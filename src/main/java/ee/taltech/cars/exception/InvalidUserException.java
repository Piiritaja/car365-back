package ee.taltech.cars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUserException extends RuntimeException {

    private String errorMessage;

    public InvalidUserException() {
    }

    public InvalidUserException(String message) {
        super(message);
        this.errorMessage = message;
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}