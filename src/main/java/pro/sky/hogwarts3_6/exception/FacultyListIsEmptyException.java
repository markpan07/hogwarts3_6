package pro.sky.hogwarts3_6.exception;

public class FacultyListIsEmptyException extends NotFoundException{

    @Override
    public String getMessage(){
        return "Faculty list is empty yet..";
    }
}
