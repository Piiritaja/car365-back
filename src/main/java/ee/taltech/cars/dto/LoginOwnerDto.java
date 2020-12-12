package ee.taltech.cars.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.annotation.Secured;

@Getter
@Setter
@Builder
public class LoginOwnerDto {

    private String email;
    private String password;
}
