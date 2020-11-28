package ee.taltech.cars.service;

import ee.taltech.cars.dto.ParamsDto;
import ee.taltech.cars.exception.AccessForbiddenException;
import ee.taltech.cars.exception.ListingNotFoundException;
import ee.taltech.cars.models.Listing;
import ee.taltech.cars.repository.ListingRepository;
import ee.taltech.cars.security.UserSessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ListingService {

    @Autowired
    private ListingRepository listingRepository;

    public List<Listing> findAll() {
        return listingRepository.findAll();
    }

    public Listing findById(UUID id) {
        return listingRepository.findById(id).orElseThrow(ListingNotFoundException::new);
    }

    public Listing save(Listing listing) {
        if (UserSessionHolder.validateAccessByID(listing.getOwner())) {
            return listingRepository.save(listing);
        } else throw new AccessForbiddenException();
    }

    public void delete(UUID id) {
        if (UserSessionHolder.validateAccessByID(findById(id).getOwner())) {
            listingRepository.delete(findById(id));
        }
    }

    public Listing update(Listing listing, UUID id) {
        if (UserSessionHolder.validateAccessByID(findById(id).getOwner())) {
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
        } return null;
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

    public ParamsDto getParameterValues() {
        ParamsDto dto = new ParamsDto();
        List<Listing> listings = listingRepository.findAll();
        for (Listing listing : listings) {
            dto.addBodyType(StringUtils.capitalize(listing.getBodyType()));
            dto.addBrand(StringUtils.capitalize(listing.getBrand()));
            dto.addModel(StringUtils.capitalize(listing.getModel()));
            dto.addColor(StringUtils.capitalize(listing.getColor()));
            dto.addDriveType(StringUtils.capitalize(listing.getDriveType()));
            dto.addFuel(StringUtils.capitalize(listing.getFuelType()));
            dto.assLocation(StringUtils.capitalize(listing.getLocation()));
            dto.addGearBoxType(StringUtils.capitalize(listing.getGearboxType()));
        }
        Collections.sort(dto.getBodyType());
        Collections.sort(dto.getBrand());
        Collections.sort(dto.getModel());
        Collections.sort(dto.getColor());
        Collections.sort(dto.getDriveType());
        Collections.sort(dto.getFuel());
        Collections.sort(dto.getGearBoxType());
        Collections.sort(dto.getLocation());
        return dto;
    }

    public List<String> getBrands() {
        return findAll()
                .stream()
                .map(Listing::getBrand)
                .collect(Collectors.toList());
    }
}
