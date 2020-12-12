package ee.taltech.cars.dto;

import ee.taltech.cars.models.Listing;
import ee.taltech.cars.security.DbRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RegisterOwnerDto{
    @ApiModelProperty(value="First name of the owner")
    private String firstName;
    @ApiModelProperty(value="Last name of owner")
    private String lastName;
    @Id
    @ApiModelProperty(value="Email address of owner")
    private String email;
    @ApiModelProperty(value = "Password of user")
    private String password;
    @ApiModelProperty(value="Phone number of owner")
    private String phone;
}
