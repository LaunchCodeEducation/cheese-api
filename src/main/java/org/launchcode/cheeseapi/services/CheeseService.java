package org.launchcode.cheeseapi.services;

import org.launchcode.cheeseapi.models.Category;
import org.launchcode.cheeseapi.models.Cheese;
import org.launchcode.cheeseapi.models.DTOs.CheeseDTO;
import org.launchcode.cheeseapi.repositories.CategoryRepository;
import org.launchcode.cheeseapi.repositories.CheeseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CheeseService {
  @Autowired
  private CheeseRepository cheeseRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  public Cheese createCheese(CheeseDTO cheeseDTO) {
    Category category = categoryRepository.getOne(cheeseDTO.getCategoryId());

    Cheese newCheese = new Cheese();
    newCheese.setCategory(category);
    newCheese.setName(cheeseDTO.getName());
    newCheese.setDescription(cheeseDTO.getDescription());

    newCheese = cheeseRepository.save(newCheese);

    category.getCheeses().add(newCheese);
    categoryRepository.save(category);

    return newCheese;
  }

  public List<Cheese> getAllCheeses() {
    return cheeseRepository.findAll();
  }
}
