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
    public Owner getUser(@PathVariable String id) {
        return ownerService.findById(id);
    }

    @PostMapping
    public Owner saveUser(@RequestBody Owner owner) {
        return ownerService.save(owner);
    }

    @PutMapping("{id}")
    public Owner updateUser(@RequestBody Owner owner, @PathVariable String id) {
        return ownerService.update(owner, id);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable String id) {
        ownerService.delete(id);
    }

}
