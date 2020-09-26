package ee.taltech.cars.controller;

import ee.taltech.cars.models.Car;
import ee.taltech.cars.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("cars")
@RestController
public class CarsController {

    @Autowired
    private CarsService carsService;

    @GetMapping()
    public List<Car> getCars() {
        return carsService.findAll();
    }

    @GetMapping("{id}")
    public Car getCar(@PathVariable String id) {
        return carsService.findById(id.toString());
    }

    @PostMapping
    public Car saveCar(@RequestBody Car car) {
        return carsService.save(car);
    }

    @PutMapping("{id}")
    public Car updateCar(@RequestBody Car car, @PathVariable Long id) {
        return carsService.update(car, id.toString());
    }

    @DeleteMapping("{id}")
    public void removeCar(@PathVariable Long id) {
        carsService.delete(id.toString());
    }

}
