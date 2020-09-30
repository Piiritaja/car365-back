package ee.taltech.cars.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Owner {
    @Id
    private final @Builder.Default
    String id = java.util.UUID.randomUUID().toString();
    private String firstName;
    private String lastName;
    @Singular
    @OneToMany
    private List<Listing> listings;


}
