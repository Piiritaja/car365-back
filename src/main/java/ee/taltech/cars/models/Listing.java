package ee.taltech.cars.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class Listing {
    private final @Builder.Default
    String id = java.util.UUID.randomUUID().toString();
    @Setter
    private String title;
    @Setter
    private String description;
    private final User owner;
    private final Car listedCar;
}
