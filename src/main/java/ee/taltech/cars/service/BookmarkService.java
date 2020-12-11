package ee.taltech.cars.service;

import ee.taltech.cars.dto.OwnerDto;
import ee.taltech.cars.models.Listing;
import ee.taltech.cars.models.Owner;
import ee.taltech.cars.repository.BookmarkRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Getter
@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    public Optional<Owner> getUser(UUID userID) {
        return bookmarkRepository.findById(userID);
    }

    public void bookmarkListing(OwnerDto owner, Listing listing) {
        Optional<Listing> match = owner.getBookmarks().stream().filter(listing1 -> (listing.getId().toString().equals(listing1.getId().toString()))).findFirst();
        if (match.isPresent()) {
            owner.getBookmarks().remove(match.get());
        } else {
            owner.getBookmarks().add(listing);
        }
    }
}
