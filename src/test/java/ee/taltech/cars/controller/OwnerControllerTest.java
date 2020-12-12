package ee.taltech.cars.controller;

import ee.taltech.cars.dto.LoginOwnerDto;
import ee.taltech.cars.dto.LoginOwnerResponse;
import ee.taltech.cars.dto.RegisterOwnerDto;
import ee.taltech.cars.models.Owner;
import ee.taltech.cars.security.JwtTokenProvider;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class OwnerControllerTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public final String userEmail = "test@usr.com";
    public UUID userId = UUID.fromString("e11b7248-7fda-4534-b291-c7ceabcb510d");


    public static final ParameterizedTypeReference<List<Owner>> LIST_OF_OWNERS = new ParameterizedTypeReference<>() {
    };

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
    void registerLoginTest() throws Exception {
        RegisterOwnerDto owner = this.getMockRegister();
        ResponseEntity<String> register = template.exchange("/user/register", HttpMethod.POST,
                new HttpEntity<>(owner), String.class);
        assertEquals(HttpStatus.CREATED, register.getStatusCode());

        LoginOwnerDto loginDetails = LoginOwnerDto.builder().email(owner.getEmail()).password(owner.getPassword()).build();
        ResponseEntity<LoginOwnerResponse> login = template.exchange("/user/login", HttpMethod.POST,
                new HttpEntity<>(loginDetails), LoginOwnerResponse.class);
        LoginOwnerResponse user = assertOK(login);
    }

    private Owner getMockOwner() {
        return Owner.builder()
                .firstName("toomas")
                .lastName("tartust")
                .email("thisemail@gmail.com")
                .password("password")
                .phone("5678420")
                .bookmarks(new ArrayList<>())
                .build();
    }

    private RegisterOwnerDto getMockRegister() {
        return RegisterOwnerDto.builder()
                .firstName("toomas")
                .lastName("tartust")
                .email("thisemail@gmail.com")
                .password("password")
                .phone("5678420")
                .build();
    }

    public HttpHeaders getUserHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + jwtTokenProvider.createTokenForTests(userEmail, userId));
        return headers;
    }

    private <T> T assertOK(ResponseEntity<T> exchange) {
        assertNotNull(exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        return exchange.getBody();
    }
}
