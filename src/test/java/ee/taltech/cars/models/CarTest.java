package ee.taltech.cars.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CarTest {
    private Car car = new Car();

    // constants for test
    private static final String MODEL = "Ferrari";
    private static final int RELEASE_YEAR = 2020;


    @BeforeEach
    void setUp() {
        car.setModel(MODEL);
        car.setReleaseYear(2020);
    }

    @Test
    void equals() {
        Car testCar = new Car();
        testCar.setModel(MODEL);
        testCar.setReleaseYear(2020);
        assertNotEquals(car, testCar);
    }

    @Test
    void getId() {
        IdPatternTest.getId(car.getId());
    }

    @Test
    void getModel() {
        assertEquals(MODEL, car.getModel());
    }

    @Test
    void getReleaserYear() {
        assertEquals(RELEASE_YEAR, car.getReleaseYear());
    }


}