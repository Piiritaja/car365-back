package ee.taltech.cars.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@ApiModel(value = "Owner", description = "Model that describes the user (owner)")
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Owner {
    @ApiModelProperty(value="ID of the user (owner)")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ApiModelProperty(value="First name of the owner")
    private String firstName;
    @ApiModelProperty(value="Last name of owner")
    private String lastName;
    @ApiModelProperty(value="Email address of owner")
    private String email;
    @ApiModelProperty(value="Phone number of owner")
    private String phone;
    @ApiModelProperty(value="Listings that the user has posted")
    @Singular
    @OneToMany
    private List<Listing> listings;


}
