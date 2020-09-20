package ee.taltech.cars.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CarTest {
    private Car car;

    // constants for test
    private static final String MODEL = "Ferrari";
    private static final int RELEASE_YEAR = 2020;


    @BeforeEach
    void setUp() {
        this.car = Car.builder().model(MODEL).releaseYear(2020).build();
    }

    @Test
    void equals() {
        Car testCar = Car.builder().model(MODEL).releaseYear(2020).build();
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