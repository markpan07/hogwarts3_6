package pro.sky.hogwarts3_6.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.hogwarts3_6.exception.StudentNotFoundException;
import pro.sky.hogwarts3_6.model.Avatar;
import pro.sky.hogwarts3_6.model.Student;
import pro.sky.hogwarts3_6.repository.AvatarRepository;
import pro.sky.hogwarts3_6.repository.StudentRepository;

import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@Service
public class AvatarService {
    //@Value("${avatars.dir}")
    private final String avatarsDir;
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    public AvatarService(AvatarRepository avatarRepository,
                         @Value("${avatars.dir}") String avatarsDir,
                         StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.avatarsDir = avatarsDir;
        this.studentRepository = studentRepository;
    }


    @Transactional
    public void upload(Long studentId, MultipartFile file) throws IOException {

        Student student = studentRepository.findById(studentId).orElseThrow(()-> new StudentNotFoundException(studentId));

        Path dir = Path.of(avatarsDir);
        if(!dir.toFile().exists()) {
            Files.createDirectories(dir);
        }

        String path = saveFile(file, student);
        Avatar avatar = avatarRepository.findByStudentId(studentId).orElse(new Avatar());
        avatar.setFilePath(path);
        avatar.setData(file.getBytes());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setStudent(student);
        avatarRepository.save(avatar);

    }

    private String saveFile(MultipartFile file, Student student) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            originalFilename = "file.jpg";
        }
        int dotIndex = originalFilename.lastIndexOf('.');
        String ext = originalFilename.substring(dotIndex + 1);
        String path = avatarsDir + "/" + student.getId() + "_" + student.getName() + "." + ext;
        try (InputStream in = file.getInputStream();
             FileOutputStream out = new FileOutputStream(path)) {
            in.transferTo(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

    @Transactional
    public Avatar find(long studentId) {
        return avatarRepository.findByStudentId(studentId).orElseThrow(()-> new StudentNotFoundException(studentId));
    }

    public Collection<Avatar> find(int page, int pageSize) {
        return avatarRepository.findAll(PageRequest.of(page, pageSize)).getContent();
    }
}
