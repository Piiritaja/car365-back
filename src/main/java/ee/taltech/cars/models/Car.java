package ee.taltech.cars.models;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@Entity
@NoArgsConstructor
public class Car {
    @Id
    private @Builder.Default
    String id = UUID.randomUUID().toString();
    private String bodyType;
    private String brand;
    private String model;
    private String color;
    private String gearboxType;
    private String fuelType;
    private String driveType;
    private int enginePower;
    private int mileage;
    private int releaseYear;
    @OneToOne
    private User owner;

    public Car(String id, String bodyType, String brand, String model, String color, String gearboxType, String fuelType,
               String driveType, int enginePower, int mileage, int releaseYear, User owner) {
        this.id = id;
        this.bodyType = bodyType;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.gearboxType = gearboxType;
        this.fuelType = fuelType;
        this.driveType = driveType;
        this.enginePower = enginePower;
        this.mileage = mileage;
        this.releaseYear = releaseYear;
        this.owner = owner;
    }
}
