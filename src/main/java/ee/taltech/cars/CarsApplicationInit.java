package ee.taltech.cars;

import ee.taltech.cars.models.Car;
import ee.taltech.cars.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CarsApplicationInit implements CommandLineRunner {
    @Autowired
    CarsRepository carsRepository;

    public void mockCars() {
        for (int i = 1; i < 50; i++) {
            Car car = new Car();
            car.setReleaseYear(1990 + i);
            car.setBodyType("Body type " + i);
            car.setBrand("Brand " + i);
            car.setColor("Color " + i);
            car.setDriveType(i + "wheel drive");
            car.setEnginePower(300 + i);
            car.setGearboxType("Automatic");
            car.setMileage(1200 + (i * 3));
            car.setFuelType("Disel");
            car.setModel("Model " + i);
            carsRepository.save(car);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        carsRepository.deleteAll();
        mockCars();

    }
}

