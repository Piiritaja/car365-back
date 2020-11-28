package ee.taltech.cars.controller;

import ee.taltech.cars.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class Users {
    @Autowired
    private static JwtTokenProvider jwtTokenProvider;

    public static final String userEmail = "test@usr.com";
    public static UUID userId = UUID.fromString("e11b7248-7fda-4534-b291-c7ceabcb510d");

    public static HttpHeaders getUserHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + jwtTokenProvider.createTokenForTests(userEmail, userId));
        return headers;
    }
    public static HttpHeaders getCustomUserHeaders(String email, UUID id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        System.out.println(id);
        headers.set("Authorization", "Bearer " + jwtTokenProvider.createTokenForTests(email, id));
        return headers;
    }
}
