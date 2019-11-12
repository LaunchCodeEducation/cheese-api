package org.launchcode.cheeseapi.controllers;

import org.launchcode.cheeseapi.controllers.utils.ResponseHelper;
import org.launchcode.cheeseapi.models.Cheese;
import org.launchcode.cheeseapi.models.DTOs.CheeseDTO;
import org.launchcode.cheeseapi.services.CheeseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = CheeseController.ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
public class CheeseController {
  public static final String ENDPOINT = "/cheeses";

  @Autowired
  private CheeseService cheeseService;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createCheese(@Valid @RequestBody CheeseDTO cheeseDTO, Errors errors) {
    if (errors.hasFieldErrors()) {
      return ResponseHelper.buildFieldErrorResponse(errors);
    }

    return ResponseEntity.ok(cheeseService.createCheese(cheeseDTO));
  }

  @GetMapping
  public List<Cheese> getAllCheeses() {
    return cheeseService.getAllCheeses();
  }

  @DeleteMapping(value = "/{cheeseId}")
  public ResponseEntity deleteCheese(@PathVariable long cheeseId) {
    cheeseService.deleteCheese(cheeseId);

    return ResponseEntity.accepted().build();
  }
}
