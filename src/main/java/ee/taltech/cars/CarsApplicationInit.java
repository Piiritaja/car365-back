package ee.taltech.cars;

import ee.taltech.cars.models.Car;
import ee.taltech.cars.models.Listing;
import ee.taltech.cars.models.Owner;
import ee.taltech.cars.repository.CarsRepository;
import ee.taltech.cars.repository.ListingRepository;
import ee.taltech.cars.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CarsApplicationInit implements CommandLineRunner {
    @Autowired
    CarsRepository carsRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    ListingRepository listingRepository;

    public void mockCars() {
        for (int i = 1; i < 50; i++) {
            Car car = Car.builder()
                    .releaseYear(1990 + i)
                    .bodyType("Body type " + i)
                    .brand("Brand " + i)
                    .color("Color " + i)
                    .driveType(i + "wheel drive")
                    .enginePower(300 + i)
                    .gearboxType("Automatic")
                    .gearboxType("Automatic")
                    .mileage(1200 + (i * 3))
                    .fuelType("Disel")
                    .model("Model " + i)
                    .build();
            carsRepository.save(car);
        }
    }

    public void mockUsers() {
        for (int i = 1; i < 50; i++) {
            Owner owner = Owner.builder()
                    .firstName("Sample")
                    .lastName("User nr." + i)
                    .build();
            ownerRepository.save(owner);
        }
    }

    public void mockListings() {
        for (int i = 1; i < 50; i++) {
            Listing listing = Listing
                    .builder()
                    .title("Listing " + i)
                    .description("It is a cool car")
                    .location("Tallinn")
                    .price(5000 + (i * 5))
                    .status("In a relationship")
                    .build();
            listingRepository.save(listing);


        }
    }

    @Override
    public void run(String... args) throws Exception {
        carsRepository.deleteAll();
        ownerRepository.deleteAll();
        listingRepository.deleteAll();
        mockCars();
        mockUsers();
        mockListings();

    }
}

