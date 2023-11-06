package pro.sky.hogwarts3_6.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.hogwarts3_6.dto.FacultyDtoIn;
import pro.sky.hogwarts3_6.dto.FacultyDtoOut;
import pro.sky.hogwarts3_6.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculties")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public FacultyDtoOut getFaculty(@PathVariable Long id) {
        return facultyService.getFaculty(id);
    }

    @PostMapping()
    public FacultyDtoOut postFaculty(@RequestBody FacultyDtoIn dto) {
        return facultyService.createFaculty(dto);
    }

    @PutMapping("{id}")
    public FacultyDtoOut updateFaculty(@PathVariable Long id,
                                    @RequestBody FacultyDtoIn dto) {
        return facultyService.updateFaculty(id, dto);
    }

    @DeleteMapping("{id}")
    public FacultyDtoOut deleteFaculty(@PathVariable Long id) {
        return facultyService.deleteFaculty(id);
    }

    @GetMapping
    public Collection<FacultyDtoOut> getAllFaculties(@RequestParam(required = false) String color){
        if(color == null || color.isBlank()){
            return facultyService.getAllFaculties();
        }
        return facultyService.findByColor(color);
    }
}
