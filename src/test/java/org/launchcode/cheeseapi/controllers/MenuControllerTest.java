package org.launchcode.cheeseapi.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.launchcode.cheeseapi.IntegrationTestConfig;
import org.launchcode.cheeseapi.models.Cheese;
import org.launchcode.cheeseapi.models.Menu;
import org.launchcode.cheeseapi.repositories.CategoryRepository;
import org.launchcode.cheeseapi.repositories.CheeseRepository;
import org.launchcode.cheeseapi.repositories.MenuRepository;
import org.launchcode.cheeseapi.utils.TestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTestConfig
@RunWith(SpringRunner.class)
public class MenuControllerTest {
  @Autowired
  private MockMvc mockRequest;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private CheeseRepository cheeseRepository;

  @Autowired
  private MenuRepository menuRepository;

  @Test
  public void getMenus() throws Exception {
    Menu menu = new Menu();
    menu.setName("test menu");
    mockRequest.perform(get(MenuController.ENDPOINT)).andExpect(status().isOk());
  }

  @Test
  public void createMenu() throws Exception {
    assertEquals(0, menuRepository.count());

    String postBody = "{ \"name\": \"test menu\", \"description\": \"test description\" }";

    mockRequest
        .perform(post(MenuController.ENDPOINT)
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(postBody)).andExpect(status().isOk());

    assertEquals(1, menuRepository.count());
  }

  @Test
  public void addCheeseToMenu() throws Exception {
    Menu menu = TestHelper.createMenu(menuRepository);
    Cheese cheese = TestHelper.createCheese(cheeseRepository, categoryRepository);

    String addCheeseEndpoint = MenuController.ENDPOINT + "/" + menu.getId() + "/cheeses";

    mockRequest
        .perform(put(addCheeseEndpoint)
                     .contentType(MediaType.APPLICATION_JSON)
                     .content("{\"cheeseId\":" + cheese.getId() + "}"))
        .andExpect(status().isNoContent());
  }
}