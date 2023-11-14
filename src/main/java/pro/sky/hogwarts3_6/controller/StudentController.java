package pro.sky.hogwarts3_6.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import pro.sky.hogwarts3_6.dto.FacultyDtoIn;
import pro.sky.hogwarts3_6.dto.FacultyDtoOut;
import pro.sky.hogwarts3_6.dto.StudentDtoIn;
import pro.sky.hogwarts3_6.dto.StudentDtoOut;
import pro.sky.hogwarts3_6.repository.StudentRepository;
import pro.sky.hogwarts3_6.service.FacultyService;
import pro.sky.hogwarts3_6.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/students")
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public StudentDtoOut getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PostMapping()
    public StudentDtoOut postStudent(@RequestBody StudentDtoIn dto) {
        return studentService.createStudent(dto);
    }

    @PutMapping("{id}")
    public StudentDtoOut updateStudent(@PathVariable Long id,
                                       @RequestBody StudentDtoIn dto) {
        return studentService.updateStudent(id, dto);
    }

    @DeleteMapping("{id}")
    public StudentDtoOut deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping()
    public Collection<StudentDtoOut> getAllStudents(@RequestParam(required = false) Integer min,
                                                    @RequestParam(required = false) Integer max,
                                                    @RequestParam(required = false) Integer age
    ) {
        if (min != null && max != null) {
            return studentService.findByAgeBetween(min, max);
        } else if (age != null) {
            return studentService.findByAge(age);
        }
        return studentService.getAllStudents();
    }

    @GetMapping("{id}/faculty")
    public FacultyDtoOut getFacultyByStudentId(@PathVariable Long id) {
        return studentService.getFacultyByStudentId(id);
    }

    @GetMapping("/count")
    public long countStudents() {
        return studentService.countStudents();
    }

    @GetMapping("/avgAge")
    public double getAverageAge() {
        return studentService.getAverageAge();
    }

    @GetMapping("/getLastFive")
    public Collection<StudentDtoOut> getLastFiveStudents(){
        return studentService.getLastFiveStudents();
    }
}
