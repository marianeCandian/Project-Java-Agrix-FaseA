package models.repositories;

import models.entities.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface Crop.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
}
