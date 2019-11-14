package org.launchcode.cheeseapi.services;

import org.launchcode.cheeseapi.models.Cheese;
import org.launchcode.cheeseapi.models.Menu;
import org.launchcode.cheeseapi.repositories.CheeseRepository;
import org.launchcode.cheeseapi.repositories.MenuRepository;
import org.launchcode.cheeseapi.services.DTOs.MenuCheeseDTO;
import org.launchcode.cheeseapi.services.DTOs.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class MenuService {
  @Autowired
  private MenuRepository menuRepository;

  @Autowired
  private CheeseRepository cheeseRepository;

  public Menu createMenu(MenuDTO menuDTO) {
    return menuRepository.save(menuDTO.convertToEntity());
  }

  public List<Menu> getAllMenus() {
    return menuRepository.findAll();
  }

  public List<Cheese> getMenuCheeses(long menuId) {
    List<Cheese> cheeses = cheeseRepository.findByMenuId(menuId);

    return cheeses;
  }

  public void addCheeseToMenu(Long menuId, MenuCheeseDTO menuCheeseDTO) {
    Menu menu = menuRepository.findById(menuId).orElseThrow(EntityNotFoundException::new);

    Cheese cheese = cheeseRepository
        .findById(menuCheeseDTO.getCheeseId())
        .orElseThrow(EntityNotFoundException::new);

    menu.addCheese(cheese);

    menuRepository.save(menu);
  }
}
