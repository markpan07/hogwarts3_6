package pro.sky.hogwarts3_6.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.hogwarts3_6.dto.FacultyDtoOut;
import pro.sky.hogwarts3_6.dto.StudentDtoIn;
import pro.sky.hogwarts3_6.dto.StudentDtoOut;
import pro.sky.hogwarts3_6.exception.FacultyNotFoundException;
import pro.sky.hogwarts3_6.exception.StudentListIsEmptyException;
import pro.sky.hogwarts3_6.exception.StudentNotFoundException;
import pro.sky.hogwarts3_6.mapper.FacultyMapper;
import pro.sky.hogwarts3_6.mapper.StudentMapper;
import pro.sky.hogwarts3_6.model.Student;
import pro.sky.hogwarts3_6.repository.AvatarRepository;
import pro.sky.hogwarts3_6.repository.FacultyRepository;
import pro.sky.hogwarts3_6.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final StudentMapper studentMapper;
    private final FacultyMapper facultyMapper;
    private final AvatarRepository avatarRepository;

    private final static Logger logger = LoggerFactory.getLogger(StudentService.class);

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
        logger.info("getStudent method was invoked");
        return studentMapper.toDto(studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id)));
    }

    public StudentDtoOut createStudent(StudentDtoIn dto) {
        logger.info("createStudent method was invoked");
        return studentMapper.toDto(studentRepository.save(studentMapper.toEntity(dto)));
    }

    public StudentDtoOut updateStudent(Long id, StudentDtoIn dto) {
        logger.info("updateStudent method was invoked");
        Student oldStudent = studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
        oldStudent.setAge(dto.getAge());
        oldStudent.setName(dto.getName());
        oldStudent.setFaculty(facultyRepository.findById(dto.getFacultyId()).orElseThrow(()-> new FacultyNotFoundException(dto.getFacultyId())));
        return studentMapper.toDto(studentRepository.save(oldStudent));
    }

    public StudentDtoOut deleteStudent(Long id) {
        logger.info("deleteStudent method was invoked");
        StudentDtoOut dto = studentMapper.toDto(studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id)));
        studentRepository.deleteById(id);
        return dto;
    }


    public Collection<StudentDtoOut> getAllStudents() {
        logger.info("getAllStudents method was invoked");
        Collection<StudentDtoOut> collection = studentRepository.findAll().stream()
                .map(entity -> studentMapper.toDto(entity))
                .collect(Collectors.toList());
        return collection;
    }

    public Collection<StudentDtoOut> findByAge(Integer age) {
        logger.info("findByAge method was invoked");
        Collection<StudentDtoOut> collection = studentRepository.findByAge(age).stream()
                .map(entity -> studentMapper.toDto(entity))
                .collect(Collectors.toList());
        return collection;
    }

    public Collection<StudentDtoOut> findByAgeBetween(Integer min, Integer max) {
        logger.info("findByAgeBetween method was invoked");
        Collection<StudentDtoOut> collection = studentRepository.findByAgeBetween(min, max).stream()
                .map(entity -> studentMapper.toDto(entity))
                .collect(Collectors.toList());
        return collection;
    }

    public FacultyDtoOut getFacultyByStudentId(Long id) {
        logger.info("getFacultyByStudentId method was invoked");
        Student student = studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
        return facultyMapper.toDto(student.getFaculty());
    }


    public long countStudents() {
        logger.info("countStudents method was invoked");
        return studentRepository.countStudents();
    }

    public double getAverageAge() {
        logger.info("getAverageAge method was invoked");
        return studentRepository.getAverageAge();
    }

    public Collection<StudentDtoOut> getLastFiveStudents(){
        logger.info("getLastFiveStudents method was invoked");
        return studentRepository.getLastFiveStudents().stream()
                .map(entity -> studentMapper.toDto(entity))
                .collect(Collectors.toList());
    }

    public Collection<String> getAllNameStartsAStream() {
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .map(name -> name.toUpperCase())
                .filter(name-> name.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }

    public double getAverageAgeStream() {
        return studentRepository.findAll().stream()
                .map(Student::getAge)
                .mapToInt(o->o)
                .average()
                .orElseThrow(()-> new StudentListIsEmptyException());
    }

    //Добавил метод конкатенации строк для увеличения времени исполнения кода
    public void printNotSync() {
        List<Student> list = studentRepository.findAll();
        System.out.println(list.get(0));
        concatinationStringInCycle();
        System.out.println(list.get(1));

        Thread thread1 = new Thread(()->{
            System.out.println(list.get(2));
            concatinationStringInCycle();
            System.out.println(list.get(3));
        });

        Thread thread2 = new Thread(()->{
            System.out.println(list.get(4));
            concatinationStringInCycle();
            System.out.println(list.get(5));
        });

        thread1.start();
        thread2.start();

    }

    public void printSync() {
        List<Student> list = studentRepository.findAll();
        printSynchronized(list.get(0));
        concatinationStringInCycle();
        printSynchronized(list.get(1));

        Thread thread1 = new Thread(()->{
            printSynchronized(list.get(2));
            concatinationStringInCycle();
            printSynchronized(list.get(3));
        });

        Thread thread2 = new Thread(()->{
            printSynchronized(list.get(4));
            concatinationStringInCycle();
            printSynchronized(list.get(5));
        });

        thread1.start();
        thread2.start();

    }

    public synchronized void printSynchronized(Object o){
        System.out.println(o);
    }

    public static void concatinationStringInCycle(){
        String s = "";
        for(int i = 0; i < 10_000; i++){
            s = s + i;
        }
    }
}
