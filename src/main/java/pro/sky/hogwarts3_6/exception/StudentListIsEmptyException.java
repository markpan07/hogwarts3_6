package pro.sky.hogwarts3_6.exception;

public class StudentListIsEmptyException extends NotFoundException {
    @Override
    public String getMessage(){
        return "Student list is empty yet..";
    }
}
