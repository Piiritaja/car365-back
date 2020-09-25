package ee.taltech.cars.service;

import ee.taltech.cars.exception.CarNotFoundException;
import ee.taltech.cars.models.Car;
import ee.taltech.cars.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarsService{

    @Autowired
    private CarsRepository carsRepository;

    public List<Car> findAll() {
        return carsRepository.findAll();
    }

    public Car findById(UUID id) {
        return carsRepository.findById(id).orElseThrow(CarNotFoundException::new);
    }

    public Car save(Car car) {
        return carsRepository.save(car);
    }

    public Car update(Car car, UUID id) {
        Car dbCar = findById(id);
        dbCar.setBodyType(car.getBodyType());
        dbCar.setBrand(car.getBrand());
        dbCar.setModel(car.getModel());
        dbCar.setColor(car.getColor());
        dbCar.setGearboxType(car.getGearboxType());
        dbCar.setFuelType(car.getFuelType());
        dbCar.setDriveType(car.getDriveType());
        dbCar.setEnginePower(car.getEnginePower());
        dbCar.setMileage(car.getMileage());
        dbCar.setReleaseYear(car.getReleaseYear());
        dbCar.setOwner(car.getOwner());
        return carsRepository.save(dbCar);
    }

    public void delete(UUID id) {
        carsRepository.delete(findById(id));
    }
}
