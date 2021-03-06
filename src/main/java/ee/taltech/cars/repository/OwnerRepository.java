package ee.taltech.cars.repository;

import ee.taltech.cars.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, UUID> {

    List<Owner> findAllByEmail(String email);
}
