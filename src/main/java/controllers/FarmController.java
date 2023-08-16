package controllers;

import dto.FarmDto;
import dto.ResponseDto;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
import services.FarmService;

/**
 * Controller Farm.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;

  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Método POST.
   */
  @PostMapping()
  public ResponseEntity<Farm> createFarm(@RequestBody FarmDto farmDto) {
    Farm newFarm = farmService.insertFarm(farmDto.toFarm());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  /**
   * Metodo para pegar todos os Farms.
   */
  @GetMapping()
  public List<FarmDto> getAllFarms() {
    List<Farm> allFarms = farmService.getAllFarms();
    return allFarms.stream()
        .map((farm) -> new FarmDto(farm.getId(), farm.getName(), farm.getSize()))
        .collect(Collectors.toList());
  }

  /**
   * Metodo para pegar um Farm pelo Id.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Farm> getFarmById(@PathVariable Long id) {
    Optional<Farm> farm = farmService.getFarmById(id);
    if (farm.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    return ResponseEntity.ok(farm.get());
  }

  /**
   * Metodo para atualizar um Farm.
   */
  public ResponseEntity<ResponseDto<Farm>> updateFarm(@PathVariable Long id,
      @RequestBody FarmDto farmDto) {
    Optional<Farm> optionalFarm = farmService.updateFarm(id, farmDto.toFarm());

    if (optionalFarm.isEmpty()) {
      ResponseDto<Farm> responseDto = new ResponseDto<>(
          String.format("Não foi encontrado a fazenda de ID %d", id), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    ResponseDto<Farm> responseDto = new ResponseDto<>(
        "Fazenda atualizada com sucesso!", optionalFarm.get());
    return ResponseEntity.ok(responseDto);
  }

}
