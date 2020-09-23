package ee.taltech.cars.repository;

import ee.taltech.cars.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
