package ee.taltech.cars.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {
    private Owner singleCarOwner;
    private Owner multipleCarsOwner;
    private Car car1;
    private Car car2;

    // constants for test
    private static final String FIRST_NAME = "Kaspar";
    private static final String LAST_NAME = "Ustav";

    @BeforeEach
    void setUp() {
        car1 = Car.builder().model("Ferrari").releaseYear(2020).build();
        car2 = Car.builder().model("Bugatti").releaseYear(2012).build();
        singleCarOwner = Owner.builder().firstName(FIRST_NAME).lastName(LAST_NAME).build();
        multipleCarsOwner = Owner.builder().firstName(FIRST_NAME).lastName(LAST_NAME).build();

    }

    Car getTestCar() {
        return Car.builder().model("Ferrari").releaseYear(2020).build();
    }

    @Test
    void getId() {
        IdPatternTest.getId(singleCarOwner.getId());
    }

    @Test
    void equals() {
        Car new_car = getTestCar();
        Car test_car = getTestCar();
        assertNotEquals(test_car, new_car);
    }

    @Test
    void getFirstName() {
        assertEquals(FIRST_NAME, singleCarOwner.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals(LAST_NAME, singleCarOwner.getLastName());
    }

}
