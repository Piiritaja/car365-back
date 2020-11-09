package ee.taltech.cars.controller;

import ee.taltech.cars.models.Owner;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class OwnerControllerTest {

    public static final ParameterizedTypeReference<List<Owner>> LIST_OF_OWNERS = new ParameterizedTypeReference<>() {};

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private MockMvc mvc;

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
        UUID id = owner.getId();
        ResponseEntity<Owner> exchangeOwner = template.exchange("/user/" + id, HttpMethod.GET,
                null, Owner.class);
        Owner resp = assertOK(exchangeOwner);
        assertEquals(name, resp.getFirstName());
    }

    @Test
    void saveAndDeleteUserTest() throws Exception {
        Owner owner = this.getMockOwner();
        ResponseEntity<Owner> exchange = template.exchange("/user", HttpMethod.POST, new HttpEntity<>(owner),
                Owner.class);
        Owner savedOwner = assertOK(exchange);
        assertEquals(owner.getFirstName(), savedOwner.getFirstName());
        assertEquals(owner.getLastName(), savedOwner.getLastName());

        template.exchange("/user/" + savedOwner.getId(), HttpMethod.DELETE, new HttpEntity<>(owner), Owner.class);
        mvc.perform(MockMvcRequestBuilders.get("/user/" + savedOwner.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Disabled
    @Test
    void updateUserTest() throws Exception {
        Owner owner = this.getMockOwner();
        ResponseEntity<Owner> exchange = template.exchange("/user", HttpMethod.POST, new HttpEntity<>(owner),
                Owner.class);
        Owner posted = assertOK(exchange);
        UUID id = posted.getId();
        owner.setFirstName("mrchangedName");
        owner.setLastName("mrChangedLastName");
        ResponseEntity<Owner> exchangeOwner = template.exchange("/user/" + id, HttpMethod.PUT,
                new HttpEntity<>(owner), Owner.class);
        Owner updatedOwner = assertOK(exchangeOwner);
        assertEquals(owner.getFirstName(), updatedOwner.getFirstName());
        assertEquals(owner.getLastName(), updatedOwner.getLastName());
        template.exchange("/user/" + updatedOwner.getId(), HttpMethod.DELETE, new HttpEntity<>(owner), Owner.class);
        mvc.perform(MockMvcRequestBuilders.get("/user/" + updatedOwner.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private Owner getMockOwner() {
        return Owner.builder()
                .firstName("toomas")
                .lastName("tartust")
                .listings(new ArrayList<>())
                .build();
    }

    private <T> T assertOK(ResponseEntity<T> exchange) {
        assertNotNull(exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        return exchange.getBody();
    }
}
