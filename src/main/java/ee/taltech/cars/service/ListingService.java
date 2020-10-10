package ee.taltech.cars.service;

import ee.taltech.cars.IdValidator;
import ee.taltech.cars.exception.ListingNotFoundException;
import ee.taltech.cars.exception.InvalidListingException;
import ee.taltech.cars.models.Listing;
import ee.taltech.cars.repository.ListingRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ListingService {
    IdValidator validator = new IdValidator();

    @Autowired
    private ListingRepository listingRepository;

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
        if (validator.validate(id)) {
            listingRepository.delete(findById(id));
        } else {
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
                listing.getPrice(),
                listing.getLocation(),
                listing.getBodyType(),
                listing.getBrand(),
                listing.getModel(),
                listing.getColor(),
                listing.getGearboxType(),
                listing.getFuelType(),
                listing.getDriveType(),
                listing.getEnginePower(),
                listing.getMileage(),
                listing.getReleaseYear(),
                listing.getEngineSize(),
                listing.getTime(),
                listing.getImages());
        return listingRepository.save(dbListing);
    }

    public List<Listing> getLatestListings(int count) {
        List<Listing> latestListings = new ArrayList<>();
        List<Listing> allListings = listingRepository.findAll();
        allListings.sort(Comparator.comparing(Listing::getTime));
        for (int i = 0; i < Math.min(count, allListings.size()); i++) {
            latestListings.add(allListings.get(i));
        }
        return latestListings;
    }

    public JSONObject getParameterValues() {
        JSONObject obj = new JSONObject();
        List<Listing> listings = listingRepository.findAll();
        for (Listing listing : listings) {
            obj.accumulate("bodyType", listing.getBodyType());
            obj.accumulate("model", listing.getModel());
            obj.accumulate("brand", listing.getBrand());
            obj.accumulate("fuel", listing.getFuelType());
            obj.accumulate("gearBoxType", listing.getGearboxType());
            obj.accumulate("driveType", listing.getDriveType());
            obj.accumulate("color", listing.getColor());
            obj.accumulate("location", listing.getLocation());
        }
        return obj;
    }

    public List<String> getBrands() {
        return findAll()
                .stream()
                .map(Listing::getBrand)
                .collect(Collectors.toList());
    }
}

