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
    private final String model;
    private final int releaseYear;
}
