package services;

import java.util.List;
import java.util.Optional;
import models.entities.Farm;
import models.repositories.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service Farm.
 */
@Service
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

  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  public Optional<Farm> getFarmById(Long id) {
    return farmRepository.findById(id);
  }

  /**
   * Metodo para atualizar um Farm.
   */
  public Optional<Farm> updateFarm(Long id, Farm farm) {
    Optional<Farm> optionalFarm = farmRepository.findById(id);

    if (optionalFarm.isPresent()) {
      Farm farmFromDb = optionalFarm.get();
      farmFromDb.setName(farm.getName());
      farmFromDb.setSize(farm.getSize());

      Farm uptatedFarm = farmRepository.save(farmFromDb);
      return Optional.of(uptatedFarm);
    }
    return optionalFarm;
  }
}
