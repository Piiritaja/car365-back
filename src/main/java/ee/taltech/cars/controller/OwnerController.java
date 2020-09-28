package ee.taltech.cars.controller;

import ee.taltech.cars.models.Owner;
import ee.taltech.cars.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("user")
@RestController
public class OwnerController {


    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public List<Owner> getUsers() {
        return ownerService.findAll();
    }

    @GetMapping("{id}")
    public Owner getUser(@PathVariable Long id) {
        return ownerService.findById(id.toString());
    }

    @PostMapping
    public Owner saveUser(@RequestBody Owner owner) {
        return ownerService.save(owner);
    }

    @PutMapping("{id}")
    public Owner updateUser(@RequestBody Owner owner, @PathVariable Long id) {
        return ownerService.update(owner, id.toString());
    }

    @DeleteMapping("{id}")
    public void deleteHero(@PathVariable Long id) {
        ownerService.delete(id.toString());
    }

}
