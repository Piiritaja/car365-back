package ee.taltech.cars.models;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Owner {
    @Id
    private final @Builder.Default
    String id = java.util.UUID.randomUUID().toString();
    private  String firstName;
    private  String lastName;
    @Singular
    @OneToMany
    private  List<Car> cars;


}
