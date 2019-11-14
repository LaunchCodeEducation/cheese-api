package org.launchcode.cheeseapi.services.DTOs;

import org.launchcode.cheeseapi.models.Category;

public class CategoryDTO extends NameEntityDTO implements Convertible<Category> {
  @Override
  public Category convertToEntity() {
    Category category = new Category();
    category.setName(this.getName());

    return category;
  }
}
