package ee.taltech.cars;

import ee.taltech.cars.models.Car;
import ee.taltech.cars.repository.CarsRepository;
import ee.taltech.cars.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CarsApplicationInit implements CommandLineRunner {
    @Autowired
    CarsRepository carsRepository;

    @Override
    public void run(String... args) throws Exception {
        carsRepository.deleteAll();
        Car car = new Car();
        car.setBodyType("Tere");
        car.setReleaseYear(2020);
        carsRepository.save(car);
    }
}

