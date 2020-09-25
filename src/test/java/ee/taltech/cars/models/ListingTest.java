package ee.taltech.cars.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListingTest {
    private Listing listing = new Listing();
    private User user = new User();
    private Car car = new Car();

    // Constants For testing
    private static final String DESCRIPTION = "This is a car";
    private static final String TITLE = "Sale";

    @BeforeEach
    void setUp() {
        this.car.setModel("Ferrari");
        this.car.setReleaseYear(2020);
        this.user.setCars(List.of(car.getId()));
        this.user.setFirstName("Kaspar");
        this.user.setLastName("Ustav");
        this.listing.setDescription(DESCRIPTION);
        this.listing.setOwner(user.getId());
        this.listing.setListedCar(car.getId());
        this.listing.setTitle(TITLE);
    }

    @Test
    void getId() {
        IdPatternTest.getId(listing.getId());
    }

    @Test
    void getTitle() {
        assertEquals(TITLE, listing.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals(DESCRIPTION, listing.getDescription());
    }

    @Test
    void getOwner() {
        assertEquals(user.getId(), listing.getOwner());
    }

    @Test
    void getListedCar() {
        assertEquals(car.getId(), listing.getListedCar());
    }
}