package ee.taltech.cars.service;

import ee.taltech.cars.exception.CarNotFoundException;
import ee.taltech.cars.models.Car;
import ee.taltech.cars.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarsService {

    @Autowired
    private CarsRepository carsRepository;

    public List<Car> findAll() {
        return carsRepository.findAll();
    }

    public Car findById(String id) {
        return carsRepository.findById(id).orElseThrow(CarNotFoundException::new);
    }

    public Car save(Car car) {
        return carsRepository.save(car);
    }

    public Car update(Car car, String id) {
        Car dbCar = findById(id);
        dbCar = new Car(dbCar.getId(),
                car.getBodyType(),
                car.getBrand(),
                car.getModel(),
                car.getColor(),
                car.getGearboxType(),
                car.getFuelType(),
                car.getDriveType(),
                car.getEnginePower(),
                car.getMileage(),
                car.getReleaseYear(),
                car.getOwnerId(),
                car.getEngineSize());
        return carsRepository.save(dbCar);
    }

    public void delete(String id) {
        carsRepository.delete(findById(id));
    }
}
