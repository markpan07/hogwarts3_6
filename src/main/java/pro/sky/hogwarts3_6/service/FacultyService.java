package pro.sky.hogwarts3_6.service;

import org.springframework.stereotype.Service;
import pro.sky.hogwarts3_6.dto.FacultyDtoIn;
import pro.sky.hogwarts3_6.dto.FacultyDtoOut;
import pro.sky.hogwarts3_6.dto.StudentDtoOut;
import pro.sky.hogwarts3_6.exception.FacultyNotFoundException;
import pro.sky.hogwarts3_6.mapper.FacultyMapper;
import pro.sky.hogwarts3_6.mapper.StudentMapper;
import pro.sky.hogwarts3_6.model.Faculty;
import pro.sky.hogwarts3_6.repository.FacultyRepository;
import pro.sky.hogwarts3_6.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;
    private final FacultyMapper facultyMapper;
    private final StudentMapper studentMapper;

    public FacultyService(FacultyRepository facultyRepository,
                          StudentRepository studentRepository,
                          FacultyMapper facultyMapper,
                          StudentMapper studentMapper) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
        this.facultyMapper = facultyMapper;
        this.studentMapper = studentMapper;
    }

    public FacultyDtoOut getFaculty(Long id) {
        return facultyMapper.toDto(facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id)));
    }

    public FacultyDtoOut createFaculty(FacultyDtoIn dto) {
        return facultyMapper.toDto(facultyRepository.save(facultyMapper.toEntity(dto)));
    }


    public FacultyDtoOut updateFaculty(Long id, FacultyDtoIn dto) {
        Faculty oldFaculty = facultyRepository.findById(id).orElseThrow(()-> new FacultyNotFoundException(id));
        oldFaculty.setColor(dto.getColor());
        oldFaculty.setName(dto.getName());
        return facultyMapper.toDto(facultyRepository.save(oldFaculty));
    }

    public FacultyDtoOut deleteFaculty(Long id) {
        FacultyDtoOut dto = facultyMapper.toDto(facultyRepository.findById(id).orElseThrow(()-> new FacultyNotFoundException(id)));
        facultyRepository.deleteById(id);
        return dto;
    }

    public Collection<FacultyDtoOut> findByColorOrName(String colorOrName) {
        List<FacultyDtoOut> collection = facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(colorOrName, colorOrName).stream()
                .map(entity -> facultyMapper.toDto(entity))
                .collect(Collectors.toList());
        return collection;
    }

    public Collection<FacultyDtoOut> getAllFaculties() {
        List<FacultyDtoOut> collection = facultyRepository.findAll().stream()
                .map(entity -> facultyMapper.toDto(entity))
                .collect(Collectors.toList());
        return collection;
    }

    public Collection<StudentDtoOut> getStudentsByFacultyId(Long id) {
        return studentRepository.findByFaculty_Id(id).stream()
                .map(entity -> studentMapper.toDto(entity))
                .collect(Collectors.toList());
    }
}