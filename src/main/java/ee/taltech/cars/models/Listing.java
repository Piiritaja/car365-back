package ee.taltech.cars.models;

import lombok.*;

import javax.persistence.*;
import java.net.URL;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Listing {
    @Id
    private final @Builder.Default
    String id = java.util.UUID.randomUUID().toString();
    private String title;
    private String description;
    private String status;
    private String owner;
    private String listedCar;
    private int price;
    private String location;
    @ElementCollection
    private List<URL> images;
}
