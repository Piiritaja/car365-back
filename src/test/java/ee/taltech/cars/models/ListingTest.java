package ee.taltech.cars.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class ListingTest {
    private Listing listing;
    private Owner owner;
    private static final String MODEL = "Ferrari";
    private static final int RELEASE_YEAR = 2020;

    // Constants For testing
    private static final String DESCRIPTION = "This is a car";
    private static final String TITLE = "Sale";

    @BeforeEach
    void setUp() {
        this.owner = Owner.builder().firstName("Kaspar").lastName("Ustav").build();
        this.listing = Listing.builder()
                .description(DESCRIPTION)
                .owner(owner.getId())
                .model(MODEL)
                .releaseYear(2020)
                .title(TITLE)
                .build();
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
    void getModel() {
        assertEquals(MODEL, listing.getModel());
    }

    @Test
    void getReleaserYear() {
        assertEquals(RELEASE_YEAR, listing.getReleaseYear());
    }
}
