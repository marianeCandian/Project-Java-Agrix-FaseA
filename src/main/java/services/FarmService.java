package services;

import models.entities.Farm;
import models.repositories.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Service Farm.
 */
public class FarmService {
  private FarmRepository farmRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  /**
   * Metodo POST.
   */
  public Farm insertFarm(Farm farm) {
    return farmRepository.save(farm);
  }
}
