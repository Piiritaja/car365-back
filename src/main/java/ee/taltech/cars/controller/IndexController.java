package ee.taltech.cars.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping
    public String getIndex() {
        return "Landing page.\n";
    }

    @GetMapping("sign_up")
    public String getSignUp() {
        return "Sign up page";
    }

    @GetMapping("log_in")
    public String getLogIn() {
        return "Login page";
    }


    @GetMapping("contact")
    public String getContact() {
        return "Contact page";
    }

    @GetMapping("car")
    public String getCar() {
        return "Cars page";
    }
}
