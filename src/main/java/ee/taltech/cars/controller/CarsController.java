package ee.taltech.cars.controller;

import ee.taltech.cars.exception.CarNotFoundException;
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


    //TODO use database instead of list
    private List<Long> numbers = List.of(1L, 2L, 3L, 4L, 5L);

    @GetMapping("{id}")
    public String getCars(@PathVariable Long id) {
        return String.format("Car: %s",
                numbers.stream()
                        .filter(nr -> nr.equals(id))
                        .findAny().orElseThrow(CarNotFoundException::new).toString());
    }

    @PostMapping
    public String saveCar(@RequestBody Long id) {
        if (!numbers.contains(id)) {
            numbers.add(id);
            return "Added: " + id;
        }
        return "ID already exists!";
    }

    @PutMapping
    public String updateCar(@PathVariable Long id) {
        //TODO make some Postgre magic to update db
        return "";
    }

    @DeleteMapping
    public void removeCar(@PathVariable Long id) {
        //TODO make some Postgre magic to update db
        numbers.stream()
                .filter(nr -> nr.equals(id))
                .findFirst()
                .ifPresent(nr -> numbers.remove(nr));
    }
}
