package ee.taltech.cars.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
public class User {
    private final String id;
    private final String firstName;
    private final String LastName;
    @Singular
    private final List<Car> cars;



}
