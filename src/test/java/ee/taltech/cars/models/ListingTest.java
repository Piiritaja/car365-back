package ee.taltech.cars.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListingTest {
    private Listing listing;
    private User user;
    private Car car;

    // Constants For testing
    private static final String DESCRIPTION = "This is a car";
    private static final String TITLE = "Sale";

    @BeforeEach
    void setUp() {
        this.car = Car.builder().model("Ferrari").releaseYear(2020).build();
        this.user = User.builder().car(car).firstName("Kaspar").lastName("Ustav").build();
        this.listing = Listing.builder().description(DESCRIPTION).owner(user).listedCar(car).title(TITLE).build();
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
        assertEquals(user, listing.getOwner());
    }

    @Test
    void getListedCar() {
        assertEquals(car, listing.getListedCar());
    }
}