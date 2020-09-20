package ee.taltech.cars.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Car {
    private final String id;
    private final User owner;
    private final String model;
    private final int releaseYear;
}
