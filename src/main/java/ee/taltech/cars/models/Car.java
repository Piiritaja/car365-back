package ee.taltech.cars.models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Car {
    private @Builder.Default
    String id = java.util.UUID.randomUUID().toString();
    private final String bodyType;
    private final String brand;
    private final String model;
    private final String color;
    private final String gearboxType;
    private final String fuelType;
    private final String driveType;
    private final int enginePower;
    private final int mileage;
    private final int releaseYear;
    private final User owner;

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
