package ee.taltech.cars.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
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
}
