package ee.taltech.cars.repository;


import ee.taltech.cars.models.Car;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CarsRepository extends JpaRepository<Car, Long> {

}
