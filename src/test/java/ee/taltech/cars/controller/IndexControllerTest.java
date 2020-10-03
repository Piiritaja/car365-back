package ee.taltech.cars.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    void getIndexTest() {
        ResponseEntity<String> exchange = template.exchange("/", HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        assertEquals("Landing page.\n", exchange.getBody());
    }

    @Test
    void signUpPageTest() {
        ResponseEntity<String> exchange = template.exchange("/sign_up", HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        assertEquals("Sign up page", exchange.getBody());
    }

    @Test
    void LogInPageTest() {
        ResponseEntity<String> exchange = template.exchange("/log_in", HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        assertEquals("Login page", exchange.getBody());
    }

    @Test
    void ContactPageTest() {
        ResponseEntity<String> exchange = template.exchange("/contact", HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        assertEquals("Contact page", exchange.getBody());
    }

    @Test
    void carPageTest() {
        ResponseEntity<String> exchange = template.exchange("/car", HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        assertEquals("Cars page", exchange.getBody());
    }
}