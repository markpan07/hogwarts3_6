package pro.sky.hogwarts3_6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.sky.hogwarts3_6.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    public List<Student> findByAge(int age);

    public List<Student> findByAgeBetween(int min, int max);

    Collection<Student> findByFaculty_Id(Long id);

    @Query(value = "select count(*) from students", nativeQuery = true)
    long countStudents();

    @Query(value = "select avg(age) from students", nativeQuery = true)
    double getAverageAge();

    @Query(value = "select * from students order by id desc limit 5", nativeQuery = true)
    Collection<Student> getLastFiveStudents();
}
