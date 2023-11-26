package pro.sky.hogwarts3_6.exception;

public class StudentNotFoundException extends NotFoundException{

    private long id;

    public StudentNotFoundException(long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Student with id = " + id + " is not found";
    }

}
