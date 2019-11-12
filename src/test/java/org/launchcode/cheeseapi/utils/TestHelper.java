package org.launchcode.cheeseapi.utils;

import org.launchcode.cheeseapi.models.Category;
import org.launchcode.cheeseapi.models.Cheese;
import org.launchcode.cheeseapi.models.Menu;
import org.launchcode.cheeseapi.repositories.CategoryRepository;
import org.launchcode.cheeseapi.repositories.CheeseRepository;
import org.launchcode.cheeseapi.repositories.MenuRepository;

public class TestHelper {
  public static String menuName = "test menu";

  public static String cheeseName = "test cheese";

  public static String categoryName = "test category";

  public static String description = "test description";

  public static Cheese createCheese(Category category, CheeseRepository cheeseRepository) {
    Cheese cheese = new Cheese();
    cheese.setName(cheeseName);
    cheese.setDescription(description);
    cheese.setCategory(category);

    return cheeseRepository.save(cheese);
  }

  public static Cheese createCheese(
      CheeseRepository cheeseRepository, CategoryRepository categoryRepository
  ) {
    Category category = createCategory(categoryRepository);

    return createCheese(category, cheeseRepository);
  }

  public static Category createCategory(CategoryRepository categoryRepository) {
    Category category = new Category();
    category.setName(categoryName);

    return categoryRepository.save(category);
  }

  public static Menu createMenu(MenuRepository menuRepository) {
    Menu menu = new Menu();
    menu.setName(menuName);

    return menuRepository.save(menu);
  }
}
