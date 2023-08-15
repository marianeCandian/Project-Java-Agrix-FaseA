package models.repositories;

import models.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio da tabela farms.
 */
@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
}
