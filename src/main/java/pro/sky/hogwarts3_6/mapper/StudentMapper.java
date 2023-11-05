package pro.sky.hogwarts3_6.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.hogwarts3_6.dto.StudentDtoIn;
import pro.sky.hogwarts3_6.dto.StudentDtoOut;
import pro.sky.hogwarts3_6.exception.FacultyNotFoundException;
import pro.sky.hogwarts3_6.model.Student;
import pro.sky.hogwarts3_6.repository.AvatarRepository;
import pro.sky.hogwarts3_6.repository.FacultyRepository;
import pro.sky.hogwarts3_6.repository.StudentRepository;

@Component
public class StudentMapper {


    private StudentRepository studentRepository;
    private FacultyRepository facultyRepository;
    private AvatarRepository avatarRepository;
    private FacultyMapper facultyMapper;

    public StudentMapper(StudentRepository studentRepository,
                         FacultyRepository facultyRepository,
                         AvatarRepository avatarRepository,
                         FacultyMapper facultyMapper) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.avatarRepository = avatarRepository;
        this.facultyMapper = facultyMapper;
    }


    public StudentDtoOut toDto(Student student) {
        StudentDtoOut dto = new StudentDtoOut();
        dto.setId(student.getId());
        dto.setAge(student.getAge());
        dto.setName(student.getName());
        dto.setFaculty(facultyMapper.toDto(student.getFaculty()));
        return dto;
    }

    public Student toEntity(StudentDtoIn dto) {
        Student entity = new Student();
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setFaculty(facultyRepository.findById(dto.getFacultyId())
                .orElseThrow(() -> new FacultyNotFoundException(dto.getFacultyId())));
        return entity;
    }
}
