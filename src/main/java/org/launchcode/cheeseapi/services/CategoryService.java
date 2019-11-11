package org.launchcode.cheeseapi.services;

import org.launchcode.cheeseapi.models.Category;
import org.launchcode.cheeseapi.models.DTOs.CategoryDTO;
import org.launchcode.cheeseapi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  public Category createCategory(CategoryDTO categoryDTO) {
    Category category = new Category();
    category.setName(categoryDTO.getName());

    return categoryRepository.save(category);
  }
}
