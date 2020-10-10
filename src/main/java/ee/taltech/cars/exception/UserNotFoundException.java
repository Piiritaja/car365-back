package ee.taltech.cars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Did not find user with given ID")
public class UserNotFoundException extends RuntimeException{

}
