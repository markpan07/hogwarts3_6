package pro.sky.hogwarts3_6.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SchoolExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(SchoolExceptionHandler.class);
    @ExceptionHandler({
            FacultyNotFoundException.class,
            StudentNotFoundException.class})

    public ResponseEntity<?> handleNotFound(NotFoundException e) {
        logger.error("NotFoundException was thrown");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
