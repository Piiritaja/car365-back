package ee.taltech.cars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason = "Range format invalid. Correct: 'int1-int2'")
public class InvalidFilterRangeException extends RuntimeException {
    public InvalidFilterRangeException(String message) {
        super(message);
    }
}
