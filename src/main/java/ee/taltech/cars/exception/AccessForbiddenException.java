package ee.taltech.cars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Access forbidden")
public class AccessForbiddenException extends RuntimeException {
}
