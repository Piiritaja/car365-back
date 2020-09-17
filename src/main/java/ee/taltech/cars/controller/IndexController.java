package ee.taltech.cars.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping
    public String index() {
        return "Congrats, you got everything up and running!";
    }
}
