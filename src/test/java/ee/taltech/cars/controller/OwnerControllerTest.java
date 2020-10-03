package ee.taltech.cars.controller;

import ee.taltech.cars.models.Owner;
import io.swagger.models.Response;
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
class OwnerControllerTest {

    public static final ParameterizedTypeReference<List<Owner>> LIST_OF_OWNERS = new ParameterizedTypeReference<>() {};

    @Autowired
    private TestRestTemplate template;

    @Test
    void getAllUsersTest() {
        ResponseEntity<List<Owner>> exchange = template.exchange("/user", HttpMethod.GET,
                null, LIST_OF_OWNERS);
        List<Owner> owners = assertOK(exchange);
        assertFalse(owners.isEmpty());
    }

    @Test
    void getOneUserTest() {
        ResponseEntity<List<Owner>> exchange = template.exchange("/user", HttpMethod.GET,
                null, LIST_OF_OWNERS);
        List<Owner> owners = exchange.getBody();
        Owner owner = owners.get(0);
        String name = owner.getFirstName();
        String id = owner.getId();
        ResponseEntity<Owner> exchangeOwner = template.exchange("/user/" + id, HttpMethod.GET,
                null, Owner.class);
        Owner resp = assertOK(exchangeOwner);
        assertEquals(name, resp.getFirstName());
    }

    @Test
    void saveUserTest() {
        Owner owner = new Owner();
        owner.setFirstName("Peep");
        owner.setLastName("Puu");
        ResponseEntity<Owner> exchange = template.exchange("/user", HttpMethod.POST, new HttpEntity<>(owner),
                Owner.class);
        Owner savedOwner = assertOK(exchange);
        assertEquals("Peep", savedOwner.getFirstName());
        assertEquals("Puu", savedOwner.getLastName());
    }

    @Test
    void updateUserTest() {
        ResponseEntity<List<Owner>> exchange = template.exchange("/user", HttpMethod.GET,
                null, LIST_OF_OWNERS);
        Owner owner = exchange.getBody().get(1);
        owner.setFirstName("Peep");
        owner.setLastName("Puu");
        String id = owner.getId();
        ResponseEntity<Owner> exchangeOwner = template.exchange("/user/" + id, HttpMethod.PUT,
                new HttpEntity<>(owner), Owner.class);
        Owner updatedOwner = assertOK(exchangeOwner);
        assertEquals("Peep", updatedOwner.getFirstName());
        assertEquals("Puu", updatedOwner.getLastName());
    }

    @Test
    void deleteUserTest() {
        ResponseEntity<List<Owner>> exchange = template.exchange("/user", HttpMethod.GET,
                null, LIST_OF_OWNERS);
        Owner owner = exchange.getBody().get(exchange.getBody().size() - 2);
        String id = owner.getId();
        ResponseEntity<Owner> exchangeOwner = template.exchange("/user/" + id, HttpMethod.DELETE,
                new HttpEntity<>(owner), Owner.class);
        assertFalse(exchangeOwner.hasBody());
    }

    private <T> T assertOK(ResponseEntity<T> exchange) {
        assertNotNull(exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        return exchange.getBody();
    }
}