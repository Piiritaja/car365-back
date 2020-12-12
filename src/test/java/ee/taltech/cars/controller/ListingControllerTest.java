package ee.taltech.cars.controller;

import ee.taltech.cars.dto.ParamsDto;
import ee.taltech.cars.models.Listing;
import ee.taltech.cars.security.JwtTokenProvider;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ListingControllerTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public final String userEmail = "test@usr.com";
    public UUID userId = UUID.fromString("e11b7248-7fda-4534-b291-c7ceabcb510d");

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
        UUID id = exchange.getBody().get(0).getId();
        UUID owner = exchange.getBody().get(0).getOwner();
        ResponseEntity<Listing> exchangeId = template.exchange("/listings/" + id, HttpMethod.GET, null, Listing.class);
        Listing listing = assertOK(exchangeId);
        assertEquals(owner, listing.getOwner());
    }

    @Test
    void putByIdTest() {
        HttpHeaders headers = getUserHeaders();
        Listing listing = this.getMockListing();
        listing.setOwner(userId);
        ResponseEntity<Listing> exchange = template.exchange("/listings", HttpMethod.POST,
                new HttpEntity<>(listing, headers), Listing.class);
        UUID id = exchange.getBody().getId();
        listing.setPrice(99999319);
        listing.setColor("thisischangedcolor");
        ResponseEntity<Listing> exchangeId = template.exchange("/listings/" + id, HttpMethod.PUT,
                new HttpEntity<>(listing, headers), Listing.class);
        Listing changedListing = assertOK(exchangeId);
        assertEquals(listing.getPrice(), changedListing.getPrice());
        assertEquals(listing.getColor(), changedListing.getColor());
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
        Listing listing = this.getMockListing();
        HttpHeaders headers = getUserHeaders();
        listing.setOwner(userId);
        ResponseEntity<Listing> exchange = template.exchange("/listings", HttpMethod.POST,
                new HttpEntity<>(listing, headers), Listing.class);
        Listing listingRecieved = assertOK(exchange);
        UUID id = listingRecieved.getId();
        Listing listing2 = this.getMockListing();
        listing2.setPrice(6942);
        listing2.setBrand("brand 2");
        listing2.setOwner(userId);
        exchange = template.exchange("/listings", HttpMethod.POST,
                new HttpEntity<>(listing2, headers), Listing.class);
        Listing listingReceived2 = assertOK(exchange);
        UUID id2 = listingReceived2.getId();

        // tests
        ResponseEntity<List<Listing>> filtered = template.exchange("/listings/filter/?brand=" + listing.getBrand(),
                HttpMethod.GET, null, LIST_OF_LISTINGS);
        assertNotNull(filtered.getBody());
        for (Listing listing1 : filtered.getBody()) {
            assertEquals(listing.getBrand(), listing1.getBrand());
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
                .andExpect(status().isBadRequest());
        mvc.perform(MockMvcRequestBuilders.delete("/listings/" + invalidId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getParamsTest() throws ParseException {
        Listing listing = getMockListing();
        HttpHeaders headers = getUserHeaders();
        listing.setOwner(userId);
        template.exchange("/listings", HttpMethod.POST,
                new HttpEntity<>(listing, headers), Listing.class);
        ResponseEntity<ParamsDto> exchangeParams = template.exchange("/listings/params", HttpMethod.GET,
                null, ParamsDto.class);
        ParamsDto result = assertOK(exchangeParams);
        List<String> models = result.getModel();
        assertTrue(models.contains("Model"));
    }

    @Test
    void latestListingsTest() {
        Listing listing = this.getMockListing();
        HttpHeaders headers = getUserHeaders();
        listing.setOwner(userId);
        ResponseEntity<Listing> postedListing = template.exchange("/listings", HttpMethod.POST,
                new HttpEntity<>(listing, headers), Listing.class);
        Listing receivedListing = assertOK(postedListing);
        ResponseEntity<List<Listing>> exchange = template.exchange("/listings/count?count=3", HttpMethod.GET,
                null, LIST_OF_LISTINGS);
        List<Listing> listings = assertOK(exchange);
    }

    private Listing getMockListing() {
        return Listing.builder()
                .title("title")
                .description("description")
                .status("status")
                .price(3000)
                .location("location")
                .bodyType("bodytype")
                .brand("brand")
                .model("model")
                .color("color")
                .gearboxType("gearbox")
                .fuelType("fueltype")
                .driveType("drivetype")
                .enginePower(100)
                .mileage(100)
                .releaseYear(2000)
                .engineSize("2.0")
                .owner(UUID.randomUUID())
                .build();
    }

    private HttpHeaders getUserHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + jwtTokenProvider.createTokenForTests(userEmail, userId));
        return headers;
    }

    private <T> T assertOK(ResponseEntity<T> exchange) {
        assertNotNull(exchange.getBody());
        System.out.println(exchange.getBody());
        System.out.println(exchange.getStatusCode());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        return exchange.getBody();
    }
}
