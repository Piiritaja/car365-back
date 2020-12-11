package ee.taltech.cars.service;

import ee.taltech.cars.dto.OwnerDto;
import ee.taltech.cars.exception.ListingNotFoundException;
import ee.taltech.cars.exception.UserNotFoundException;
import ee.taltech.cars.models.Listing;
import ee.taltech.cars.models.Owner;
import ee.taltech.cars.repository.BookmarkRepository;
import ee.taltech.cars.repository.ListingRepository;
import ee.taltech.cars.repository.OwnerRepository;
import ee.taltech.cars.security.MyUser;
import ee.taltech.cars.security.UserSessionHolder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;
    @Autowired
    private ListingRepository listingRepository;
    @Autowired
    private OwnerService ownerService;

    public Optional<Owner> getUser(UUID userID) {
        return bookmarkRepository.findById(userID);
    }

    public Owner bookmarkListing(UUID listing) {
        MyUser user = UserSessionHolder.getLoggedInUser();
        OwnerDto userDb = ownerService.findById(user.getId());
        Optional<Listing> match = userDb
                .getBookmarks()
                .stream()
                .filter(listing1 -> (listing.toString().equals(listing1.getId().toString())))
                .findFirst();
        List<Listing> bookmarks = new ArrayList<>(userDb.getBookmarks());
        if (match.isPresent()) {
            bookmarks.remove(match.get());
            userDb.setBookmarks(bookmarks);
        } else {
            Optional<Listing> listingDb = listingRepository.findById(listing);
            if (listingDb.isEmpty()) {
                throw new ListingNotFoundException();
            } else {
                bookmarks.add(listingDb.get());
                userDb.setBookmarks(bookmarks);
            }
        }
        return ownerService.update(userDb, userDb.getId());

    }
}
