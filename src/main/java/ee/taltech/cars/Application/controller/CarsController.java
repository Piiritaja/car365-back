package ee.taltech.cars.Application.controller;

import ee.taltech.cars.Application.exception.CarNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("cars")
@RestController
public class CarsController {

    //TODO use database instead of list
    private List<Integer> numbers = List.of(1, 2, 3, 4, 5);

    @GetMapping("{id}")
    public String getCars(@PathVariable Integer id) {
        return String.format("Car: %s",
                numbers.stream()
                        .filter(nr -> nr.equals(id))
                        .findAny().orElseThrow(CarNotFoundException::new).toString());
    }

    @PostMapping
    public String saveCar(@RequestBody Integer id) {
        if (!numbers.contains(id)) {
            numbers.add(id);
            return "Added: " + id;
        }
        return "ID already exists!";
    }
}
