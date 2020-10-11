package ee.taltech.cars.a_theory.question6.chocolate;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Cake {

    private Long id;
    private String size;
    private String sweetness;
    private List<String> ingredients;
    private List<String> toppings;
}
