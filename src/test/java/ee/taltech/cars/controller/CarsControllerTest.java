package ee.taltech.cars.controller;

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
class CarsControllerTest {

    public static final ParameterizedTypeReference<List<Car>> LIST_OF_CARS = new ParameterizedTypeReference<>() {};
    public static final ParameterizedTypeReference<List<String>> LIST_OF_BRANDS = new ParameterizedTypeReference<>() {};

    @Autowired
    private TestRestTemplate template;

    @Test
    void queryCarsTest() {
        ResponseEntity<List<Car>> exchange = template.exchange("/cars", HttpMethod.GET,
                null, LIST_OF_CARS);
        List<Car> cars = assertOK(exchange);
        assertFalse(cars.isEmpty());
        Car car = cars.get(0);
    }

    @Test
    void queryBrandsTest() {
        ResponseEntity<List<String>> exchangeBrands = template.exchange("/cars/brands", HttpMethod.GET,
                null, LIST_OF_BRANDS);
        List<String> brands = assertOK(exchangeBrands);
        assertFalse(brands.isEmpty());
    }

    @Test
    void queryOneCarTest() {
        ResponseEntity<List<Car>> exchange = template.exchange("/cars", HttpMethod.GET,
                null, LIST_OF_CARS);
        List<Car> cars = exchange.getBody();
        Car car = cars.get(0);
        String id = car.getId();
        ResponseEntity<Car> exchangeQueryCar = template.exchange("/cars/" + id,
                HttpMethod.GET, null, Car.class);
        Car resp = assertOK(exchangeQueryCar);
        assertFalse(resp.getId().isEmpty());
    }

    @Test
    void saveCarTest() {
        Car car = new Car("ok", "sedan", "audi", "100", "red", "manual",
                "diesel", "rear-wheel drive", 0, 0, 1989, "0");
        ResponseEntity<Car> exchangeSaveCar = template.exchange("/cars", HttpMethod.POST, new HttpEntity<>(car),
                Car.class);
        Car savedCar = assertOK(exchangeSaveCar);
        assertEquals("audi", savedCar.getBrand());
        assertEquals("manual", car.getGearboxType());
    }

    @Test
    void updateCarTest() {
        ResponseEntity<List<Car>> exchange = template.exchange("/cars", HttpMethod.GET,
                null, LIST_OF_CARS);
        List<Car> cars = exchange.getBody();
        Car car = cars.get(1);
        car.setColor("brown");
        car.setMileage(10000);
        String id = car.getId();
        ResponseEntity<Car> exchangeUpdateCar = template.exchange("/cars/" + id, HttpMethod.PUT,
                new HttpEntity<>(car), Car.class);
        Car updatedCar = assertOK(exchangeUpdateCar);
        assertEquals("brown", updatedCar.getColor());
        assertEquals(10000, updatedCar.getMileage());
    }

    @Test
    void deleteCarTest() {
        ResponseEntity<List<Car>> exchange = template.exchange("/cars", HttpMethod.GET,
                null, LIST_OF_CARS);
        List<Car> cars = exchange.getBody();
        Car car = cars.get(cars.size() - 2);
        String id = car.getId();
        ResponseEntity<Car> exchangeDeletedCar = template.exchange("/cars/" + id, HttpMethod.DELETE,
                new HttpEntity<>(car), Car.class);
        assertNull(exchangeDeletedCar.getBody());
    }

    private <T> T assertOK(ResponseEntity<T> exchange) {
        assertNotNull(exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        return exchange.getBody();
    }
}
