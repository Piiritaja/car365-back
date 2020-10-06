package ee.taltech.cars.service;

import ee.taltech.cars.models.Car;
import ee.taltech.cars.models.Listing;
import ee.taltech.cars.repository.CarsRepository;
import ee.taltech.cars.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FilterService {

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private CarsRepository carsRepository;

    public List<Listing> getFiltered(Listing listing, Car car, String priceRange, String yearRange, String powerRange) {
        List<Listing> listings = listingRepository.findAll();
        listings = listings.stream()
                .filter(dbListing -> validateFilterListing(dbListing, listing, priceRange))
                .collect(Collectors.toList());
        Map<Listing, Car> suitableCars = new HashMap<>();
        for (Listing sortedListing : listings) {
            Optional<Car> dbCar = carsRepository.findById(sortedListing.getListedCar());
            dbCar.ifPresent(value -> suitableCars.put(sortedListing, value));
        }
        listings = suitableCars.keySet().stream()
                .filter(dbListing -> validateFilterCar(suitableCars.get(dbListing), car, yearRange, powerRange))
                .collect(Collectors.toList());
        return listings;
    }

    private boolean validateFilterListing(Listing dbListing, Listing listing, String priceRange) {
        if (listing.getLocation() != null && !Objects.equals(listing.getLocation(), dbListing.getLocation())) {
            return false;
        }
        if (priceRange != null) {
                String[] priceRangeArray = priceRange.split("-");
                return dbListing.getPrice() >= Integer.parseInt(priceRangeArray[0])
                        && dbListing.getPrice() <= Integer.parseInt(priceRangeArray[1]);
        }
        return true;
    }

    private boolean validateFilterCar(Car dbCar, Car car, String yearRange, String powerRange) {
        List<String> filterParams = Arrays.asList(car.getBrand(), car.getBodyType(), car.getModel(),
                car.getFuelType(),
                car.getGearboxType(),
                car.getDriveType(),
                car.getColor());
        List<String> dbCarParams = Arrays.asList(dbCar.getBrand(), dbCar.getBodyType(), dbCar.getModel(),
                dbCar.getFuelType(),
                dbCar.getGearboxType(),
                dbCar.getDriveType(),
                dbCar.getColor());
        for (int i = 0; i < filterParams.size(); i++) {
            String filter = filterParams.get(i);
            if (filter != null) {
                if (!filter.equals(dbCarParams.get(i))) {
                    return false;
                }
            }
        }
        if (yearRange != null) {
            String[] yearRangeArray = yearRange.split("-");
            if (dbCar.getReleaseYear() < Integer.parseInt(yearRangeArray[0])
                    || dbCar.getReleaseYear() > Integer.parseInt(yearRangeArray[1])) {
                return false;
            }
        }
        if (powerRange != null) {
            String[] powerRangeArray = powerRange.split("-");
            return dbCar.getEnginePower() >= Integer.parseInt(powerRangeArray[0])
                    && dbCar.getEnginePower() <= Integer.parseInt(powerRangeArray[1]);
        }
        return true;
    }
}
