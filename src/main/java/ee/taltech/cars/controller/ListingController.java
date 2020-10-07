package ee.taltech.cars.controller;

import ee.taltech.cars.models.Listing;
import ee.taltech.cars.service.FilterService;
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

    @Autowired
    private FilterService filterService;

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

    @GetMapping("filter")
    public List<Listing> getFilteredListings(Listing listing,
                                             String priceRange,
                                             String yearRange,
                                             String powerRange) {
        // pricerange "200-3000"
        // yearrange 'year1-year2'
        // powerange 'power1-power2'
        return filterService.getFiltered(listing, priceRange, yearRange, powerRange);
    }

    @GetMapping("/brands")
    public List<String> getBrands() {
        return listingService.findAll()
                .stream()
                .map(Listing::getBrand)
                .collect(Collectors.toList());
    }

    @GetMapping("/params")
    public String getParams() {
        return listingService.getParameterValues().toString();
    }
}
