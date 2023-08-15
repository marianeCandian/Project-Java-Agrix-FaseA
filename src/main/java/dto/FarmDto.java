package dto;

import models.entities.Farm;

/**
 * DTO para Farm.
 */
public record FarmDto(Long id, String name, Double size) {

  public Farm toFarm() {
    return new Farm(id, name, size);
  }
}
