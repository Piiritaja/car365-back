package ee.taltech.cars.controller;

import ee.taltech.cars.models.Listing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ListingControllerTest {

    public static final ParameterizedTypeReference<List<Listing>> LIST_OF_LISTINGS
            = new ParameterizedTypeReference<>() {
    };
    public static final ParameterizedTypeReference<List<String>> LIST_OF_BRANDS = new ParameterizedTypeReference<>() {
    };
    @Autowired
    private MockMvc mvc;

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
        listing.setColor("brown");
        ResponseEntity<Listing> exchangeId = template.exchange("/listings/" + id, HttpMethod.PUT,
                new HttpEntity<>(listing), Listing.class);
        Listing changedListing = assertOK(exchangeId);
        assertEquals(3000, changedListing.getPrice());
        assertEquals("brown", changedListing.getColor());
    }

    @Test
    void postListingTest() {
        Listing listing = Listing.builder()
                .price(3000)
                .bodyType("sedan")
                .brand("audi")
                .build();
        listing.setPrice(3000);
        ResponseEntity<Listing> exchange = template.exchange("/listings", HttpMethod.POST,
                new HttpEntity<>(listing), Listing.class);
        Listing addedListing = assertOK(exchange);
        assertEquals(3000, addedListing.getPrice());
        assertEquals("sedan", addedListing.getBodyType());
        assertEquals("audi", addedListing.getBrand());
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

    @Test
    void queryBrandsTest() {
        ResponseEntity<List<String>> exchangeBrands = template.exchange("/listings/brands", HttpMethod.GET,
                null, LIST_OF_BRANDS);
        List<String> brands = assertOK(exchangeBrands);
        assertFalse(brands.isEmpty());
    }

    @Test
    void testFilter() {
        // setup
        String brand = "audi";
        Listing listing = Listing.builder()
                .price(3000)
                .bodyType("sedan")
                .brand(brand)
                .build();
        ResponseEntity<Listing> exchange = template.exchange("/listings", HttpMethod.POST,
                new HttpEntity<>(listing), Listing.class);
        String id = exchange.getBody().getId();

        Listing listing2 = Listing.builder()
                .price(6942)
                .bodyType("sedan")
                .brand(brand + "2")
                .build();
        exchange = template.exchange("/listings", HttpMethod.POST,
                new HttpEntity<>(listing), Listing.class);
        String id2 = exchange.getBody().getId();

        // tests
        ResponseEntity<List<Listing>> filtered = template.exchange("/listings/filter/?brand=" + brand,
                HttpMethod.GET, null, LIST_OF_LISTINGS);
        assertNotNull(filtered.getBody());
        for (Listing listing1 : filtered.getBody()) {
            assertEquals(brand, listing1.getBrand());
        }


        String priceRange = "2000-4311";
        filtered = template.exchange("/listings/filter/?priceRange=" + priceRange,
                HttpMethod.GET, null, LIST_OF_LISTINGS);
        String[] priceRangeArray = priceRange.split("-");
        assertNotNull(filtered.getBody());
        for (Listing listing1 : filtered.getBody()) {
            assertTrue(listing1.getPrice() >= Integer.parseInt(priceRangeArray[0])
                    && listing1.getPrice()<= Integer.parseInt(priceRangeArray[1]));
        }
        template.exchange("/listings/" + id, HttpMethod.DELETE,
                new HttpEntity<>(listing), Listing.class);
        template.exchange("/listings/" + id2, HttpMethod.DELETE,
                new HttpEntity<>(listing), Listing.class);
    }

    @Test
    void filterExceptionTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/listings/filter?yearRange=12-asd")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnsupportedMediaType());

        mvc.perform(MockMvcRequestBuilders.get("/listings/filter?priceRange=ii")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnsupportedMediaType());

        mvc.perform(MockMvcRequestBuilders.get("/listings/filter?powerRange=32")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void listingExceptionTest() throws Exception {
        String invalidId = "thisisaninvalidIDforListings123469";
        mvc.perform(MockMvcRequestBuilders.get("/listings/" + invalidId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        mvc.perform(MockMvcRequestBuilders.delete("/listings/" + invalidId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    private <T> T assertOK(ResponseEntity<T> exchange) {
        assertNotNull(exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        return exchange.getBody();
    }
}
