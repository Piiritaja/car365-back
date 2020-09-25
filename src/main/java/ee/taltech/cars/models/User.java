package ee.taltech.cars.models;

import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class User {
    private String firstName;
    private String lastName;
    @Singular
    private List<UUID> cars;
    private UUID id = UUID.randomUUID();

    @Id
    public UUID getId() {
        return id;
    }
}
