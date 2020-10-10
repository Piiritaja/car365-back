package ee.taltech.cars.controller;

import ee.taltech.cars.models.Listing;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "IndexController")
@RestController
public class IndexController {

    @ApiOperation(value = "Index page")
    @GetMapping
    public String getIndex() {
        return "Landing page.\n";
    }

    @ApiOperation(value = "Sign up page", notes = "Unused in part 1")
    @GetMapping("sign_up")
    public String getSignUp() {
        return "Sign up page";
    }

    @ApiOperation(value = "Login page", notes = "Unused in part 1")
    @GetMapping("log_in")
    public String getLogIn() {
        return "Login page";
    }

    @ApiOperation(value = "Contact page", notes = "Unused in part 1")
    @GetMapping("contact")
    public String getContact() {
        return "Contact page";
    }

    @ApiOperation(value = "Cars page", notes = "Unused in part 1")
    @GetMapping("car")
    public String getCar() {
        return "Cars page";
    }
}
