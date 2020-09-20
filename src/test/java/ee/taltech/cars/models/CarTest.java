package ee.taltech.cars.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CarTest {
    private Car car;


    @BeforeEach
    void setUp() {
        this.car = Car.builder().model("Ferrari").releaseYear(2020).build();
    }

    @Test
    void equals() {
        Car testCar = Car.builder().model("Ferrari").releaseYear(2020).build();
        assertNotEquals(car, testCar);
    }

    @Test
    void getId() {
        IdPatternTest.getId(car.getId());
    }

    @Test
    void getModel() {
        assertEquals("Ferrari", car.getModel());
    }

    @Test
    void getReleaserYear() {
        assertEquals(2020, car.getReleaseYear());
    }


}