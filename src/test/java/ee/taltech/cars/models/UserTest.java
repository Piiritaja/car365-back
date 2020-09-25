package ee.taltech.cars.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private final User singleCarUser = new User();
    private final User multipleCarsUser = new User();
    private final Car car1 = new Car();
    private final Car car2 = new Car();

    // constants for test
    private static final String FIRST_NAME = "Kaspar";
    private static final String LAST_NAME = "Ustav";

    @BeforeEach
    void setUp() {
        car1.setModel("Ferrari");
        car1.setReleaseYear(2020);
        car2.setModel("Bugatti");
        car2.setReleaseYear(2012);
        singleCarUser.setCars(List.of(car1.getId()));
        singleCarUser.setFirstName(FIRST_NAME);
        singleCarUser.setLastName(LAST_NAME);
        multipleCarsUser.setCars(List.of(car1.getId(), car2.getId()));
        multipleCarsUser.setFirstName(FIRST_NAME);
        multipleCarsUser.setLastName(LAST_NAME);

    }

    Car getTestCar() {
        Car car = new Car();
        car.setModel("Ferrari");
        car.setReleaseYear(2020);
        return car;
    }

    @Test
    void getId() {
        IdPatternTest.getId(singleCarUser.getId());
    }

    @Test
    void equals() {
        Car new_car = getTestCar();
        Car test_car = getTestCar();
        assertNotEquals(test_car, new_car);
    }

    @Test
    void getFirstName() {
        assertEquals(FIRST_NAME, singleCarUser.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals(LAST_NAME, singleCarUser.getLastName());
    }

    @Test
    void getCarsSizeSingleCar() {
        assertEquals(1, singleCarUser.getCars().size());
    }

    @Test
    void getCarsSingleCarRightCar() {
        assertEquals(car1.getId(), singleCarUser.getCars().get(0));
    }

    @Test
    void getCarsSizeTwoCars() {
        assertEquals(2, multipleCarsUser.getCars().size());
    }

    @Test
    void getCarsTwoCars() {
        List<UUID> test_cars = multipleCarsUser.getCars();
        assertTrue(test_cars.contains(car1.getId()) && test_cars.contains(car2.getId()));
    }
}