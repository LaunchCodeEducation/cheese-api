package org.launchcode.cheeseapi.controllers;

import org.launchcode.cheeseapi.models.Category;
import org.launchcode.cheeseapi.repositories.CategoryRepository;
import org.launchcode.cheeseapi.utils.ResponseUtils;
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
@RequestMapping("/categories")
public class CategoryController {
  @Autowired
  private CategoryRepository categoryRepository;

  @PostMapping(consumes = "application/json", produces = "application/json") // Create
  public ResponseEntity createCategory(@Valid @RequestBody Category newCategory, Errors errors) {
    if (errors.hasErrors()) {
      return ResponseUtils.buildFieldErrorResponseEntity(errors);
    }

    return ResponseEntity.ok(categoryRepository.save(newCategory));
  }

  @GetMapping() // Read
  public List<Category> getCategories() {
    return categoryRepository.findAll();
  }
}
