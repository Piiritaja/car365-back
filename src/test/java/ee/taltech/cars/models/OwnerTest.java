package ee.taltech.cars.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {
    private Owner singleCarOwner;
    private Owner multipleCarsOwner;
    private Listing car1;
    private Listing car2;

    // constants for test
    private static final String FIRST_NAME = "Kaspar";
    private static final String LAST_NAME = "Ustav";

    @BeforeEach
    void setUp() {
        car1 = Listing.builder().model("Ferrari").releaseYear(2020).build();
        car2 = Listing.builder().model("Bugatti").releaseYear(2012).build();
        singleCarOwner = Owner.builder().firstName(FIRST_NAME).lastName(LAST_NAME).build();
        multipleCarsOwner = Owner.builder().firstName(FIRST_NAME).lastName(LAST_NAME).build();

    }

    Listing getTestCar() {
        return Listing.builder().model("Ferrari").releaseYear(2020).build();
    }

    @Test
    void getId() {
        IdPatternTest.getId(singleCarOwner.getId());
    }

    @Test
    void equals() {
        Listing new_car = getTestCar();
        Listing test_car = getTestCar();
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
