package org.launchcode.cheeseapi.controllers;

import org.launchcode.cheeseapi.models.Cheese;
import org.launchcode.cheeseapi.utils.ResponseUtils;
import org.launchcode.cheeseapi.repositories.CheeseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/cheeses")
public class CheeseController {
  @Autowired
  private CheeseRepository cheeseRepository;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity createCheese(@Valid @RequestBody Cheese newCheese, Errors errors) {
    if (errors.hasErrors()) {
      return ResponseUtils.buildFieldErrorResponseEntity(errors);
    }

    return ResponseEntity.ok(cheeseRepository.save(newCheese));
  }

  @GetMapping()
  public List<Cheese> getCheeses() {
    return cheeseRepository.findAll();
  }
}
