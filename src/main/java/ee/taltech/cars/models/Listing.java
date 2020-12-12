package ee.taltech.cars.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@ApiModel(value = "Listing", description = "Model that describes the car listing")
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Listing {
    @ApiModelProperty(value = "Random ID given to listing")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ApiModelProperty(value = "Title of the listing")
    private String title;
    @ApiModelProperty(value = "Written description of the listing")
    private String description;
    @ApiModelProperty(value = "Status of the listing")
    private String status;
    @ApiModelProperty(value = "Boolean of listing being premium")
    private boolean premium;
    @ApiModelProperty(value = "User who posted the listing")
    private UUID owner;
    @ApiModelProperty(value = "Price of the car in listing")
    private int price;
    @ApiModelProperty(value = "Location of the car in the listing")
    private String location;
    @ApiModelProperty(value = "Body type of the car in the listing")
    private String bodyType;
    @ApiModelProperty(value = "Brand of the car in the listing")
    private String brand;
    @ApiModelProperty(value = "Model of the car in the listing")
    private String model;
    @ApiModelProperty(value = "Color of the car in the listing")
    private String color;
    @ApiModelProperty(value = "Gearbox type of the car in the listing")
    private String gearboxType;
    @ApiModelProperty(value = "Fuel type of the car in the listing")
    private String fuelType;
    @ApiModelProperty(value = "Drive type of the car in the listing")
    private String driveType;
    @ApiModelProperty(value = "Engine power in KW-s of the car in the listing")
    private int enginePower;
    @ApiModelProperty(value = "Mileage in KM-s of the car in the listing")
    private int mileage;
    @ApiModelProperty(value = "Release year of the car in the listing")
    private int releaseYear;
    @ApiModelProperty(value = "Engine size in liters of the car in the listing")
    private String engineSize;
    private final @Builder.Default
    @ApiModelProperty(value = "Listing creation time")
    Long time = System.currentTimeMillis();
    @ApiModelProperty(value = "Images of the car in the listing. Max amount is 4")
    @ElementCollection
    private List<String> images;

    public void addImage(String val) {
        this.images.add(val);
    }
}
