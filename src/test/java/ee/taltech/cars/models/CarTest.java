package ee.taltech.cars.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    private Car car;
    private final static String GUID_REGEX = "\b[0-9a-f]{8}\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\b[0-9a-f]{12}\b";


    @BeforeEach
    void setUp() {
        this.car = Car.builder().model("Ferrari").releaseYear(2020).build();
    }

    @Test
    void equals() {
        Car testCar = Car.builder().model("Ferrari").releaseYear(2020).build();

    }

    @Test
    void getId() {
        String newUID = car.getId();
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