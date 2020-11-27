package ee.taltech.cars.controller;

import ee.taltech.cars.dto.LoginOwnerDto;
import ee.taltech.cars.dto.LoginOwnerResponse;
import ee.taltech.cars.dto.RegisterOwnerDto;
import ee.taltech.cars.models.Owner;
import ee.taltech.cars.security.Roles;
import ee.taltech.cars.service.OwnerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public Owner getUser(@ApiParam(value = "ID of the user to retrieve", required = false) @PathVariable UUID id) {
        return ownerService.findById(id);
    }

    @Secured({Roles.PREMIUM, Roles.USER, Roles.ADMIN})
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

    @Secured({Roles.PREMIUM, Roles.USER, Roles.ADMIN})
    @ApiOperation(value = "Update user by ID",
            notes = "Saves given user (owner) to the given ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User updated"),
            @ApiResponse(code =  400, message = "User is invalid"),
            @ApiResponse(code = 404, message = "ID not found in database")
    })
    @PutMapping("{id}")
    public Owner updateUser(@ApiParam(value = "User to be saved") @RequestBody Owner owner,
                            @ApiParam(value = "ID to which the new user is assigned") @PathVariable UUID id) {
        return ownerService.update(owner, id);
    }

    @Secured(Roles.ADMIN)
    @ApiOperation(value = "Delete user by ID",
            notes = "Deletes user (owner) by given ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User deleted"),
            @ApiResponse(code =  404, message = "Did not find user with given ID")
    })
    @DeleteMapping("{id}")
    public void deleteUser(@ApiParam(value = "ID of the user to delete") @PathVariable UUID id) {
        ownerService.delete(id);
    }

    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody RegisterOwnerDto registerDto){
        ownerService.save(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("login")
    public LoginOwnerResponse login(@RequestBody LoginOwnerDto loginDto){
        return ownerService.login(loginDto);
    }


}
