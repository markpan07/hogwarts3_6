package pro.sky.hogwarts3_6.service;

import org.springframework.stereotype.Service;
import pro.sky.hogwarts3_6.dto.FacultyDtoOut;
import pro.sky.hogwarts3_6.dto.StudentDtoIn;
import pro.sky.hogwarts3_6.dto.StudentDtoOut;
import pro.sky.hogwarts3_6.exception.FacultyNotFoundException;
import pro.sky.hogwarts3_6.exception.StudentNotFoundException;
import pro.sky.hogwarts3_6.mapper.FacultyMapper;
import pro.sky.hogwarts3_6.mapper.StudentMapper;
import pro.sky.hogwarts3_6.model.Student;
import pro.sky.hogwarts3_6.repository.AvatarRepository;
import pro.sky.hogwarts3_6.repository.FacultyRepository;
import pro.sky.hogwarts3_6.repository.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final StudentMapper studentMapper;
    private final FacultyMapper facultyMapper;
    private final AvatarRepository avatarRepository;

    public StudentService(StudentRepository studentRepository,
                          FacultyRepository facultyRepository,
                          StudentMapper studentMapper,
                          FacultyMapper facultyMapper,
                          AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.studentMapper = studentMapper;
        this.facultyMapper = facultyMapper;
        this.avatarRepository = avatarRepository;
    }

    public StudentDtoOut getStudent(Long id) {
        return studentMapper.toDto(studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id)));
    }

    public StudentDtoOut createStudent(StudentDtoIn dto) {
        return studentMapper.toDto(studentRepository.save(studentMapper.toEntity(dto)));
    }

    public StudentDtoOut updateStudent(Long id, StudentDtoIn dto) {
        Student oldStudent = studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
        oldStudent.setAge(dto.getAge());
        oldStudent.setName(dto.getName());
        oldStudent.setFaculty(facultyRepository.findById(dto.getFacultyId()).orElseThrow(()-> new FacultyNotFoundException(dto.getFacultyId())));
        return studentMapper.toDto(studentRepository.save(oldStudent));
    }

    public StudentDtoOut deleteStudent(Long id) {
        StudentDtoOut dto = studentMapper.toDto(studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id)));
        studentRepository.deleteById(id);
        return dto;
    }


    public Collection<StudentDtoOut> getAllStudents() {
        Collection<StudentDtoOut> collection = studentRepository.findAll().stream()
                .map(entity -> studentMapper.toDto(entity))
                .collect(Collectors.toList());
        return collection;
    }

    public Collection<StudentDtoOut> findByAge(Integer age) {
        Collection<StudentDtoOut> collection = studentRepository.findByAge(age).stream()
                .map(entity -> studentMapper.toDto(entity))
                .collect(Collectors.toList());
        return collection;
    }

    public Collection<StudentDtoOut> findByAgeBetween(Integer min, Integer max) {
        Collection<StudentDtoOut> collection = studentRepository.findByAgeBetween(min, max).stream()
                .map(entity -> studentMapper.toDto(entity))
                .collect(Collectors.toList());
        return collection;
    }

    public FacultyDtoOut getFacultyByStudentId(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
        return facultyMapper.toDto(student.getFaculty());
    }


    public long countStudents() {
        return studentRepository.countStudents();
    }

    public double getAverageAge() {
        return studentRepository.getAverageAge();
    }

    public Collection<StudentDtoOut> getLastFiveStudents(){
        return studentRepository.getLastFiveStudents().stream()
                .map(entity -> studentMapper.toDto(entity))
                .collect(Collectors.toList());
    }
}
