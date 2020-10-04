package ee.taltech.cars.models;

import lombok.*;

import javax.persistence.*;
import java.net.URL;
import java.sql.Timestamp;
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
    private final @Builder.Default
    Long time = System.nanoTime();
    @ElementCollection
    private List<String> images;
}
