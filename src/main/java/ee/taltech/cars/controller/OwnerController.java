package ee.taltech.cars.controller;

import ee.taltech.cars.models.Listing;
import ee.taltech.cars.models.Owner;
import ee.taltech.cars.service.OwnerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "User Controller")
@RequestMapping("user")
@RestController
public class OwnerController {


    @Autowired
    private OwnerService ownerService;

    @ApiOperation(value = "Get all users",
            notes = "Returns all users (owners) that are found in database",
            response = Owner.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Users received"),
    })
    @GetMapping
    public List<Owner> getUsers() {
        return ownerService.findAll();
    }

    @ApiOperation(value = "Get user by ID",
            notes = "Returns user (owner) by given ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User received"),
            @ApiResponse(code =  404, message = "Did not find user with given ID")
    })
    @GetMapping("{id}")
    public Owner getUser(@ApiParam(value = "ID of the user to retrieve", required = true) @PathVariable String id) {
        return ownerService.findById(id);
    }

    @ApiOperation(value = "Save new user",
            notes = "Saves new user (owner) to database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User saved"),
            @ApiResponse(code = 400, message = "Given user is invalid")
    })
    @PostMapping
    public Owner saveUser(@ApiParam(value = "The user to save", required = true) @RequestBody Owner owner) {
        return ownerService.save(owner);
    }

    @ApiOperation(value = "Update user by ID",
            notes = "Saves given user (owner) to the given ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User updated"),
            @ApiResponse(code =  400, message = "User is invalid"),
            @ApiResponse(code = 404, message = "ID not found in database")
    })
    @PutMapping("{id}")
    public Owner updateUser(@ApiParam(value = "User to be saved") @RequestBody Owner owner,
                            @ApiParam(value = "ID to which the new user is assigned") @PathVariable String id) {
        return ownerService.update(owner, id);
    }

    @ApiOperation(value = "Delete user by ID",
            notes = "Deletes user (owner) by given ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User deleted"),
            @ApiResponse(code =  404, message = "Did not find user with given ID")
    })
    @DeleteMapping("{id}")
    public void deleteUser(@ApiParam(value = "ID of the user to delete") @PathVariable String id) {
        ownerService.delete(id);
    }

}
