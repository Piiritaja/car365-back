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
    private int price;
    private String location;
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
    private String engineSize;
    private final @Builder.Default
    Long time = System.nanoTime();
    @ElementCollection
    private List<String> images;
}
