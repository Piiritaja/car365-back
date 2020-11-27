package ee.taltech.cars.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @GetMapping("users")
    public String user(){
        return "User url";
    }

    @GetMapping("admin")
    public String admin(){
        return "Admin url";
    }
}
