package dto;

import models.entities.Crop;

/**
 * CropDto has been created.
 */

public record CropDto(Long id, String name, Long farmId, Double plantedArea) {


  public Crop toCrop(Long farmId) {
    return new Crop(id, name, plantedArea, farmId);
  }

  public Crop toCrop() {
    return new Crop(id, name, plantedArea, farmId);
  }

}
