package pro.sky.hogwarts3_6.dto;

import java.util.Objects;

public class StudentDtoOut {
    private Long id;
    private String name;
    private int age;
    private FacultyDtoOut faculty;

    public StudentDtoOut(Long id, String name, int age, FacultyDtoOut faculty) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.faculty = faculty;
    }

    public StudentDtoOut() {
    }

    public FacultyDtoOut getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyDtoOut faculty) {
        this.faculty = faculty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentDtoOut)) return false;
        StudentDtoOut that = (StudentDtoOut) o;
        return age == that.age && Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    @Override
    public String toString() {
        return "StudentDtoOut{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
