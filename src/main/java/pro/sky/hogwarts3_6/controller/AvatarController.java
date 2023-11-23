package pro.sky.hogwarts3_6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.hogwarts3_6.model.Avatar;
import pro.sky.hogwarts3_6.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping("{studentId}")
    public void upload(@PathVariable Long studentId, MultipartFile file) throws IOException {
        avatarService.upload(studentId, file);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<byte[]> find(@PathVariable long studentId) {
        Avatar avatar = avatarService.find(studentId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());


    }

    @GetMapping("/file/{studentId}")
    public void findFile(@PathVariable long studentId, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.find(studentId);

        File file = new File(avatar.getFilePath());
        if (!file.exists()) {
            response.setStatus(500);
            return;
        }
        response.setContentType(avatar.getMediaType());
        response.setContentLength((int) avatar.getFileSize());
        try (OutputStream out = response.getOutputStream();
             InputStream in = new FileInputStream(avatar.getFilePath())) {
            in.transferTo(out);
        }
    }

    @GetMapping
    public Collection<Avatar> findAvatars(@RequestParam int page, @RequestParam int pageSize) {
        return avatarService.find(page - 1, pageSize);
    }
}
