package ee.taltech.cars.models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Car {

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
    private UUID owner;
    private UUID id = UUID.randomUUID();

    public Car() {
    }

    @Id
    public UUID getId() {
        return id;
    }
}
