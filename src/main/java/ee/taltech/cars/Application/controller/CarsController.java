package ee.taltech.cars.Application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarsController {

    @GetMapping("/cars")
    public String getCars() {
        return "Cars page";
    }
}
