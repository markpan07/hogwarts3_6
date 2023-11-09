package pro.sky.hogwarts3_6.dto;

import java.util.Objects;

public class FacultyDtoIn {
    private String name;
    private String color;

    public FacultyDtoIn(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public FacultyDtoIn() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "FacultyDtoIn{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FacultyDtoIn)) return false;
        FacultyDtoIn that = (FacultyDtoIn) o;
        return Objects.equals(name, that.name) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }
}
