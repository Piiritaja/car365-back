package ee.taltech.cars.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.List;

@Builder
@Getter
public class Listing {
    private final @Builder.Default
    String id = java.util.UUID.randomUUID().toString();
    @Setter
    private String title;
    @Setter
    private String description;
    @Setter
    private String status;
    private final User owner;
    private final Car listedCar;
    private final int price;
    private final String location;
    private final List<URL> images;
}
