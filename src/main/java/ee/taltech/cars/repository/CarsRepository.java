package ee.taltech.cars.repository;


import ee.taltech.cars.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Repository
public interface CarsRepository extends JpaRepository<Car, UUID> {
}
