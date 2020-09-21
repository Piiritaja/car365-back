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
    private final User owner;
    private final String bodyType;
    private final String brand;
    private final String model;
    private final int releaseYear;
    private final String gearboxType;
    private final String fuelType;
    private final int enginePower;
    private final int mileage;
    private final String driveType;
}
