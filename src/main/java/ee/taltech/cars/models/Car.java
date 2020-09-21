package ee.taltech.cars.models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Car {
    private final @Builder.Default
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
}
