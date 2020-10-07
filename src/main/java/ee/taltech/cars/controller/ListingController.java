package ee.taltech.cars.controller;


import ee.taltech.cars.models.Listing;
import ee.taltech.cars.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("listings")
public class ListingController {

    @Autowired
    private ListingService listingService;

    @GetMapping
    public List<Listing> getAll() {
        return listingService.findAll();
    }

    @GetMapping("{id}")
    public Listing getById(@PathVariable String id) {
        return listingService.findById(id);
    }

    @PutMapping("{id}")
    public Listing putById(@RequestBody Listing listing, @PathVariable String id) {
        return listingService.update(listing, id);
    }

    @PostMapping
    public Listing postListing(@RequestBody Listing listing) {
        return listingService.save(listing);
    }

    @DeleteMapping("{id}")
    public void deleteListing(@PathVariable String id) {
        listingService.delete(id);
    }

    @GetMapping("count")
    public List<Listing> getNumberOfLatestListings(@RequestParam(defaultValue = "10") int count) {
        return listingService.getLatestListings(count);
    }

    @GetMapping("/brands")
    public List<String> getBrands() {
        return listingService.findAll()
                .stream()
                .map(Listing::getBrand)
                .collect(Collectors.toList());
    }
}
