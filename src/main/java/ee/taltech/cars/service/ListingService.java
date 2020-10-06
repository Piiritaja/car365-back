package ee.taltech.cars.service;

import ee.taltech.cars.IdValidator;
import ee.taltech.cars.exception.ListingNotFoundException;
import ee.taltech.cars.exception.InvalidListingException;
import ee.taltech.cars.models.Car;
import ee.taltech.cars.models.Listing;
import ee.taltech.cars.repository.CarsRepository;
import ee.taltech.cars.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ListingService {
    IdValidator validator = new IdValidator();

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private CarsRepository carsRepository;

    public List<Listing> findAll() {
        return listingRepository.findAll();
    }

    public Listing findById(String id) {
        if (validator.validate(id)) return listingRepository.findById(id).orElseThrow(ListingNotFoundException::new);
        throw new InvalidListingException();
    }

    public Listing save(Listing listing) {
        if (validator.validate(listing.getId())) {
            return listingRepository.save(listing);
        }
        throw new InvalidListingException();
    }

    public void delete(String id) {
        if (validator.validate(id)) { listingRepository.delete(findById(id)); }
        else {
            throw new InvalidListingException();
        }
    }

    public Listing update(Listing listing, String id) {
        Listing dbListing = findById(id);
        dbListing = new Listing(dbListing.getId(),
                listing.getTitle(),
                listing.getDescription(),
                listing.getStatus(),
                listing.getOwner(),
                listing.getListedCar(),
                listing.getPrice(),
                listing.getLocation(),
                listing.getTime(),
                listing.getImages());
        return listingRepository.save(dbListing);
    }

    public List<Listing> getLatestListings(int count) {
        List<Listing> latestListings = new ArrayList<>();
        List<Listing> allListings = listingRepository.findAll();
        allListings.sort(Comparator.comparing(Listing::getTime).reversed());
        for (int i = 0; i < Math.min(count, allListings.size()); i++) {
            latestListings.add(allListings.get(i));
        }
        return latestListings;
    }

    public List<Listing> getFiltered(Listing listing, Car car) {
        List<Listing> listings = listingRepository.findAll();
        listings = listings.stream()
                .filter(dbListing -> validateFilterListing(dbListing, listing))
                .collect(Collectors.toList());
        Map<Listing, Car> suitableCars = new HashMap<>();
        for (Listing sortedListing : listings) {
            Optional<Car> dbCar = carsRepository.findById(sortedListing.getListedCar());
            dbCar.ifPresent(value -> suitableCars.put(sortedListing, value));
        }
        listings = suitableCars.keySet().stream()
                .filter(dbListing -> validateFilterCar(suitableCars.get(dbListing), car))
                .collect(Collectors.toList());
        return listings;
    }

    private boolean validateFilterListing(Listing dbListing, Listing listing) {
        if (listing.getPrice() == 0 || listing.getPrice() == dbListing.getPrice()) {
            return true;
        }
        return true;
    }

    private boolean validateFilterCar(Car dbCar, Car car) {
        return false;
    }
}
