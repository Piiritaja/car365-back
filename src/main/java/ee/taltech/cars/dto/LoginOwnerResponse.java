package ee.taltech.cars.dto;

import ee.taltech.cars.models.Listing;
import ee.taltech.cars.security.DbRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
public class LoginOwnerResponse {
    @ApiModelProperty(value="ID of the user (owner)")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ApiModelProperty(value="Email address of owner")
    private String email;
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "User role")
    private DbRole role;
    @ApiModelProperty(value = "JWT token")
    private String token;
}
