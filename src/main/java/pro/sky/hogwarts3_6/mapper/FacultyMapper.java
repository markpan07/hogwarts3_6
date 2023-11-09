package pro.sky.hogwarts3_6.mapper;

import org.springframework.stereotype.Component;
import pro.sky.hogwarts3_6.dto.FacultyDtoIn;
import pro.sky.hogwarts3_6.dto.FacultyDtoOut;
import pro.sky.hogwarts3_6.model.Faculty;
import pro.sky.hogwarts3_6.repository.FacultyRepository;

@Component
public class FacultyMapper {

    private FacultyRepository facultyRepository;


    public FacultyDtoOut toDto(Faculty faculty) {
        FacultyDtoOut dto = new FacultyDtoOut();
        dto.setColor(faculty.getColor());
        dto.setId(faculty.getId());
        dto.setName(faculty.getName());
        dto.setId(faculty.getId());
        return dto;
    }

    public Faculty toEntity(FacultyDtoIn dto) {
        Faculty entity = new Faculty();
        entity.setColor(dto.getColor());
        entity.setName(dto.getName());
        return entity;

    }
}
