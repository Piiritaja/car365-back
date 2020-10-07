package ee.taltech.cars.controller;

import ee.taltech.cars.models.Listing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ListingControllerTest {

    public static final ParameterizedTypeReference<List<Listing>> LIST_OF_LISTINGS
            = new ParameterizedTypeReference<>() {};

    @Autowired
    private TestRestTemplate template;

    @Test
    void getAllTest() {
        ResponseEntity<List<Listing>> exchange = template.exchange("/listings", HttpMethod.GET,
                null, LIST_OF_LISTINGS);
        List<Listing> listings = assertOK(exchange);
        assertFalse(listings.isEmpty());
    }

    @Test
    void getByIdTest() {
        ResponseEntity<List<Listing>> exchange = template.exchange("/listings", HttpMethod.GET,
                null, LIST_OF_LISTINGS);
        String id = exchange.getBody().get(0).getId();
        String owner = exchange.getBody().get(0).getOwner();
        ResponseEntity<Listing> exchangeId = template.exchange("/listings/" + id, HttpMethod.GET, null, Listing.class);
        Listing listing = assertOK(exchangeId);
        assertEquals(owner, listing.getOwner());
    }

    @Test
    void putByIdTest() {
        ResponseEntity<List<Listing>> exchange = template.exchange("/listings", HttpMethod.GET,
                null, LIST_OF_LISTINGS);
        Listing listing = exchange.getBody().get(0);
        String id = listing.getId();
        listing.setPrice(3000);
        ResponseEntity<Listing> exchangeId = template.exchange("/listings/" + id, HttpMethod.PUT,
                new HttpEntity<>(listing), Listing.class);
        Listing changedListing = assertOK(exchangeId);
        assertEquals(3000, changedListing.getPrice());
    }

    @Test
    void postListingTest() {
        Listing listing = new Listing();
        listing.setPrice(3000);
        ResponseEntity<Listing> exchange = template.exchange("/listings", HttpMethod.POST,
                new HttpEntity<>(listing), Listing.class);
        Listing addedListing = assertOK(exchange);
        assertEquals(3000, addedListing.getPrice());
    }

    @Test
    void deleteListingTest() {
        ResponseEntity<List<Listing>> exchange = template.exchange("/listings", HttpMethod.GET, null,
                LIST_OF_LISTINGS);
        List<Listing> listings = exchange.getBody();
        Listing listing = listings.get(listings.size() - 2);
        String id = listing.getId();
        ResponseEntity<Listing> exchangeListing = template.exchange("/listings/" + id, HttpMethod.DELETE,
                new HttpEntity<>(listing), Listing.class);
        assertNull(exchangeListing.getBody());
    }

    private <T> T assertOK(ResponseEntity<T> exchange) {
        assertNotNull(exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        return exchange.getBody();
    }
}
