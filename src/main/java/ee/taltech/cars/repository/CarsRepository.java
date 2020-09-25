package ee.taltech.cars.repository;


import ee.taltech.cars.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarsRepository extends JpaRepository<Car, String> {
}
