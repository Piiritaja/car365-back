package ee.taltech.cars.controller;

import ee.taltech.cars.dto.ParamsDto;
import ee.taltech.cars.models.Listing;
import ee.taltech.cars.service.FilterService;
import ee.taltech.cars.service.ListingService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Api(value = "Listing Controller")
@RestController
@RequestMapping({"listings", "listings2"})
public class ListingController {

    @Autowired
    private ListingService listingService;

    @Autowired
    private FilterService filterService;

    @ApiOperation(value = "Gets all listings",
            notes = "Returns all listings that are found in database",
            response = Listing.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listings received"),
    })
    @GetMapping
    public List<Listing> getAll() {
        return listingService.findAll();
    }

    @ApiOperation(value = "Gets listing by ID",
            notes = "Returns the listing by ID if present",
            response = Listing.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listing received"),
            @ApiResponse(code = 400, message = "Invalid ID given"),
            @ApiResponse(code = 404, message = "Listing not found by given ID")
    })
    @GetMapping("{id}")
    public Listing getById(@ApiParam(value = "ID of the listing to retrieve from database") @PathVariable UUID id) {
        return listingService.findById(id);
    }

    @ApiOperation(value = "Update listing",
            notes = "Takes the listing and id and updates the listing that corresponds" +
                    " to the id to match the listing that is given as a parameter")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listing with given ID updated according to the parameter"),
            @ApiResponse(code = 400, message = "Invalid ID given"),
            @ApiResponse(code = 404, message = "Listing not found by given ID")
    })
    @PutMapping("{id}")
    public Listing putById(@ApiParam(value = "Listing with new parameters") @RequestBody Listing listing,
                           @ApiParam(value = "ID to update") @PathVariable UUID id) {
        return listingService.update(listing, id);
    }

    @ApiOperation(value = "Save new listing to database",
            notes = "Takes the listing and saves it to the database",
            response = Listing.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listing successfully saved to database")
    })
    @PostMapping
    public Listing postListing(@ApiParam(value = "Listing to save") @RequestBody Listing listing) {
        return listingService.save(listing);
    }

    @ApiOperation(value = "Delete listing by ID",
            notes = "Deletes the listing with given ID from database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listing successfully deleted"),
            @ApiResponse(code = 400, message = "Invalid ID given"),
            @ApiResponse(code = 404, message = "Listing not found by given ID")
    })
    @DeleteMapping("{id}")
    public void deleteListing(@ApiParam(value = "ID of the listing to delete") @PathVariable UUID id) {
        listingService.delete(id);
    }

    @ApiOperation(value = "Get latest listings",
            notes = "Get the 'count' latest listings from database. Default value for 'count' is 10." +
                    "When there are less than 'count' listings in database, all of them are returned")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found listings"),
            @ApiResponse(code = 404, message = "No listings found")
    })
    @GetMapping("count")
    public List<Listing> getNumberOfLatestListings(@ApiParam(value = "Number of listings to retrieve")
                                                   @RequestParam(defaultValue = "10") int count) {
        return listingService.getLatestListings(count);
    }

    @ApiOperation(value = "Get filtered listings",
            notes = "Checks given path variables and returns listings that match these criteria." +
                    " Ranges required format: 'bottom-top'.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Filtered successfully"),
            @ApiResponse(code = 415, message = "Filter range format invalid. Correct: 'bottom-top'")
    })
    @GetMapping("filter")
    public List<Listing> getFilteredListings(@ApiParam(value = "Filter matches these parameters")
                                                     Listing listing,
                                             @ApiParam(value = "Price range for filtering 'price1-price2'")
                                                     String priceRange,
                                             @ApiParam(value = "Year range for filtering 'year1-year2'")
                                                     String yearRange,
                                             @ApiParam(value = "Engine power range for filtering 'power1-power2'")
                                                     String powerRange) {
        return filterService.getFiltered(listing, priceRange, yearRange, powerRange);
    }

    @ApiOperation(value = "Get all brands from database",
            notes = "Gets all listing.brand-s that are in use in the database. Duplicates are not avoided, " +
                    "may contain them.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Received all brands from database"),
    })
    @GetMapping("/brands")
    public List<String> getBrands() {
        return listingService.getBrands();
    }

    @ApiOperation(value = "Get all listing parameters",
            notes = "Gets all listing parameters that are in use in the database. Duplicates are not avoided, " +
                    "may contain them.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Received all parameters from database"),
    })
    @GetMapping("/params")
    public ParamsDto getParams() {
        return listingService.getParameterValues();
    }
}
