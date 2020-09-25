package ee.taltech.cars.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.net.URL;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Listing {

    private String title;
    private String description;
    private String status;
    private UUID owner;
    private UUID listedCar;
    private int price;
    private String location;
    private List<URL> images;
    private UUID id = UUID.randomUUID();

    @Id
    public UUID getId() {
        return id;
    }
}
