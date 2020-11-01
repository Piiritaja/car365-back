package ee.taltech.cars.a_theory.question6.art;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class Painting {

    private Long id;
    private String author;
    private String name;
    private LocalDate drawnAt;
    private LocalDate boughtAt;
    private BigDecimal price;
}
