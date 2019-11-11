package org.launchcode.cheeseapi.controllers;

import org.launchcode.cheeseapi.models.Category;
import org.launchcode.cheeseapi.models.DTOs.CategoryDTO;
import org.launchcode.cheeseapi.services.CategoryService;
import org.launchcode.cheeseapi.utils.ResponseUtils;
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

@RestController()
@RequestMapping("/categories")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createCategory(@Valid @RequestBody CategoryDTO categoryDTO, Errors errors) {
    if (errors.hasErrors()) {
      return ResponseUtils.buildFieldErrorResponseEntity(errors);
    }

    return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
  }

  @GetMapping()
  public List<Category> getCategories() {
    return categoryService.getAllCategories();
  }
}
