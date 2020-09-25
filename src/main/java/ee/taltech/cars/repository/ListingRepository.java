package ee.taltech.cars.repository;

import ee.taltech.cars.models.Car;
import ee.taltech.cars.models.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListingRepository extends JpaRepository<Listing, String> {

}
