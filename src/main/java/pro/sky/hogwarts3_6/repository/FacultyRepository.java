package pro.sky.hogwarts3_6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.hogwarts3_6.model.Faculty;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    public List<Faculty> findByColor(String color);

    public List<Faculty> findByColorIgnoreCaseOrNameIgnoreCase(String color, String name);
}
