package pro.sky.hogwarts3_6.exception;

public class FacultyNotFoundException extends NotFoundException{
    private long id;

    public FacultyNotFoundException(long id) {
        this.id = id;

    }

    @Override
    public String getMessage(){
        return "Faculty with id = " + id + " is not found";
    }
}
