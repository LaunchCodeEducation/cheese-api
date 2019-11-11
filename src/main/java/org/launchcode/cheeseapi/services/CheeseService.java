package org.launchcode.cheeseapi.services;

import org.launchcode.cheeseapi.models.Category;
import org.launchcode.cheeseapi.models.Cheese;
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

  public Cheese createCheese(Cheese cheeseData) {
    Category category = categoryRepository.getOne(cheeseData.getCategoryId());

    cheeseData.setCategory(category);
    Cheese newCheese = cheeseRepository.save(cheeseData);

    category.getCheeses().add(newCheese);
    categoryRepository.save(category);

    return newCheese;
  }

  public List<Cheese> getAllCheeses() {
    return cheeseRepository.findAll();
  }
}
