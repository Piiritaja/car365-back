package ee.taltech.cars.models;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Getter
public class ListingData {
    private Listing listingItem;
    private MultipartFile file;
}
