package ee.taltech.cars.service;

import ee.taltech.cars.exception.InvalidFilterRangeException;
import ee.taltech.cars.models.Listing;
import ee.taltech.cars.repository.ListingRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Service
public class FilterService {

    @Autowired
    private ListingRepository listingRepository;

    public List<Listing> getFiltered(Listing listing, String priceRange, String yearRange, String powerRange) {
        List<Listing> listings = listingRepository.findAll();
        listings = listings.stream()
                .filter(dbListing -> validateFilterListing(dbListing, listing, priceRange, yearRange, powerRange))
                .collect(Collectors.toList());
        return listings;
    }

    private boolean validateFilterListing(Listing dbListing, Listing listing,
                                          String priceRange, String yearRange, String powerRange) {

        List<String> filterParams = Arrays.asList(listing.getLocation(), listing.getBrand(),
                listing.getBodyType(), listing.getModel(), listing.getFuelType(),
                listing.getGearboxType(), listing.getDriveType(), listing.getColor());

        List<String> dbListingParams = Arrays.asList(dbListing.getLocation(), dbListing.getBrand(),
                dbListing.getBodyType(), dbListing.getModel(), dbListing.getFuelType(),
                dbListing.getGearboxType(), dbListing.getDriveType(), dbListing.getColor());

        for (int i = 0; i < filterParams.size(); i++) {
            String filter = filterParams.get(i);
            if (filter != null) {
                if (!filter.equalsIgnoreCase(dbListingParams.get(i))) {
                    return false;
                }
            }
        }
        try {
            if (priceRange != null) {
                String[] priceRangeArray = priceRange.split("-");
                return dbListing.getPrice() >= Integer.parseInt(priceRangeArray[0])
                        && dbListing.getPrice() <= Integer.parseInt(priceRangeArray[1]);
            }
            if (yearRange != null) {
                String[] yearRangeArray = yearRange.split("-");
                if (dbListing.getReleaseYear() < Integer.parseInt(yearRangeArray[0])
                        || dbListing.getReleaseYear() > Integer.parseInt(yearRangeArray[1])) {
                    return false;
                }
            }
            if (powerRange != null) {
                String[] powerRangeArray = powerRange.split("-");
                return dbListing.getEnginePower() >= Integer.parseInt(powerRangeArray[0])
                        && dbListing.getEnginePower() <= Integer.parseInt(powerRangeArray[1]);
            }
        } catch (Exception e) {
            throw new InvalidFilterRangeException("Range format invalid. Correct: 'int1-int2'");
        }
        return true;
    }

}
