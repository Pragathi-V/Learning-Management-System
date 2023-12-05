package upskill.casestudy.studentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmailAlreadyExistException extends RuntimeException{

    private String message;

    public EmailAlreadyExistException(String message) {
        super(message);
    }

}
