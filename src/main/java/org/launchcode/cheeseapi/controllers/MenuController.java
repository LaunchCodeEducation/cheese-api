package org.launchcode.cheeseapi.controllers;

import org.launchcode.cheeseapi.controllers.utils.ResponseUtils;
import org.launchcode.cheeseapi.models.DTOs.MenuCheeseDTO;
import org.launchcode.cheeseapi.models.DTOs.MenuDTO;
import org.launchcode.cheeseapi.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping(value = MenuController.ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {
  public static final String ENDPOINT = "/menus";

  @Autowired
  private MenuService menuService;

  @GetMapping
  public ResponseEntity getMenus() {
    return ResponseEntity.ok(menuService.getAllMenus());
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createMenu(@Valid @RequestBody MenuDTO menuDTO, Errors errors) {
    if (errors.hasFieldErrors()) {
      return ResponseUtils.buildFieldErrorResponseEntity(errors);
    }

    return ResponseEntity.ok(menuService.createMenu(menuDTO));
  }

  @PutMapping(value = "/{menuId}/cheeses", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity addCheeseToMenu(@PathVariable Long menuId, @RequestBody MenuCheeseDTO menuCheeseDTO) {

    try {
      menuService.addCheeseToMenu(menuId, menuCheeseDTO);
      return ResponseEntity.noContent().build();
    } catch (EntityNotFoundException error) {
      return ResponseEntity.status(400).body(error.getMessage());
    }
  }
}
