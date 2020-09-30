package ee.taltech.cars.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListingTest {
    private Listing listing;
    private Owner owner;
    private Car car;

    // Constants For testing
    private static final String DESCRIPTION = "This is a car";
    private static final String TITLE = "Sale";

    @BeforeEach
    void setUp() {
        this.car = Car.builder().model("Ferrari").releaseYear(2020).build();
        this.owner = Owner.builder().firstName("Kaspar").lastName("Ustav").build();
        this.listing = Listing.builder().description(DESCRIPTION).owner(owner.getId()).listedCar(car.getId()).title(TITLE).build();
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
        assertEquals(owner.getId(), listing.getOwner());
    }

    @Test
    void getListedCar() {
        assertEquals(car.getId(), listing.getListedCar());
    }
}
