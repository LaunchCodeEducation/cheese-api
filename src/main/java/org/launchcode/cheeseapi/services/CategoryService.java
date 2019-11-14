package org.launchcode.cheeseapi.services;

import org.launchcode.cheeseapi.models.Category;
import org.launchcode.cheeseapi.repositories.CategoryRepository;
import org.launchcode.cheeseapi.services.DTOs.CategoryDTO;
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
    return categoryRepository.save(categoryDTO.convertToEntity());
  }
}
