package ee.taltech.cars.service;

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
}
