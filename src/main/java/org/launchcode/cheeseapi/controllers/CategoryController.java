package org.launchcode.cheeseapi.controllers;

import org.launchcode.cheeseapi.controllers.utils.ResponseHelper;
import org.launchcode.cheeseapi.models.Category;
import org.launchcode.cheeseapi.models.DTOs.CategoryDTO;
import org.launchcode.cheeseapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = CategoryController.ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {
  public static final String ENDPOINT = "/categories";

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public List<Category> getCategories() {
    return categoryService.getAllCategories();
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createCategory(@Valid @RequestBody CategoryDTO categoryDTO, Errors errors) {
    if (errors.hasErrors()) {
      return ResponseHelper.buildFieldErrorResponse(errors);
    }

    return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
  }
}
