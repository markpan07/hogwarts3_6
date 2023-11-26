package pro.sky.hogwarts3_6.dto;

import java.util.Objects;

public class StudentDtoIn {

    private String name;
    private int age;

    private Long facultyId;

    public StudentDtoIn() {
    }

    public StudentDtoIn(String name, int age, Long facultyId) {
        this.name = name;
        this.age = age;
        this.facultyId = facultyId;
    }

    public StudentDtoIn(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public Long getFacultyId() {
        return facultyId;
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
        if (!(o instanceof StudentDtoIn)) return false;
        StudentDtoIn that = (StudentDtoIn) o;
        return age == that.age && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "StudentDtoIn{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
