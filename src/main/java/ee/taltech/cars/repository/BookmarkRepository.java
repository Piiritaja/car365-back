package ee.taltech.cars.repository;

import ee.taltech.cars.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookmarkRepository extends JpaRepository<Owner, UUID> {
}
