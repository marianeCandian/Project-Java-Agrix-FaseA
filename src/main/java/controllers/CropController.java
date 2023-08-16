package controllers;

import dto.CropDto;
import dto.FarmDto;
import dto.ResponseDto;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import models.entities.Crop;
import models.entities.Farm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.CropService;
import services.FarmService;

/**
 * Controller Crop.
 */
@RestController
@RequestMapping()
public class CropController {

  private CropService cropService;

  private FarmService farmService;

  /**
   * Constructor CropCotroller.
   */
  @Autowired
  public CropController(CropService cropService, FarmService farmService) {
    this.cropService = cropService;
    this.farmService = farmService;
  }

  /**
   * Metodo para pegar todos os Crops.
   */
  @GetMapping("/crops")
  public List<CropDto> getAllCrops() {
    List<Crop> allCrops = cropService.getAllCrops();
    return allCrops.stream()
        .map((crop) -> new CropDto(crop.getId(), crop.getName(), crop.getFarmId(),
            crop.getPlantedArea()))
        .collect(Collectors.toList());
  }

  /**
   * Metodo para pega um Crop.
   */
  @GetMapping("/crops/{id}")
  public ResponseEntity<Crop> getCropById(@PathVariable Long id) {
    Optional<Crop> optionalCrop = cropService.getCropById(id);

    if (optionalCrop.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    return ResponseEntity.ok(optionalCrop.get());
  }

  /**
   * Metodo para pegar uma lista de Crops atraves do farmId.
   */
  @GetMapping("/farms/{farmId}/crops")
  public ResponseEntity<List<Crop>> getCropsByFarmId(@PathVariable Long farmId) {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    if (optionalFarm.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    List<Crop> allCrops = cropService.getAllCrops();
    List listStream = allCrops.stream()
        .filter(crop -> crop.getFarmId().equals(farmId))
        .map((crop) -> new CropDto(
            crop.getId(), crop.getName(), crop.getFarmId(), crop.getPlantedArea()))
        .collect(Collectors.toList());

    return ResponseEntity.ok(listStream);
  }

  /**
   * Metodo para atualizar um Farm.
   */
  @PostMapping("/farms/{farmId}/crops")
  public ResponseEntity<Crop> createCrop(@PathVariable Long farmId, @RequestBody CropDto cropDto) {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    if (optionalFarm.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    Crop newCrop = cropService.insertCrop(cropDto.toCrop(farmId));
    return ResponseEntity.status(HttpStatus.CREATED).body(newCrop);
  }


}
