package ee.taltech.cars.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
public class User {
    private final @Builder.Default
    String id = java.util.UUID.randomUUID().toString();
    private final String firstName;
    private final String lastName;
    @Singular
    private final List<Car> cars;



}
