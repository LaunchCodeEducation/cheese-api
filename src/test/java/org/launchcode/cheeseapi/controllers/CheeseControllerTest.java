package org.launchcode.cheeseapi.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.launchcode.cheeseapi.IntegrationTestConfig;
import org.launchcode.cheeseapi.models.Category;
import org.launchcode.cheeseapi.models.Cheese;
import org.launchcode.cheeseapi.repositories.CategoryRepository;
import org.launchcode.cheeseapi.repositories.CheeseRepository;
import org.launchcode.cheeseapi.utils.TestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTestConfig
@RunWith(SpringRunner.class)
public class CheeseControllerTest {

  @Autowired
  private CheeseRepository cheeseRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private MockMvc mockRequest;

  private Category category;

  @Before
  public void setup() {
    // prevent recreating if it already exists
    if (category != null) return;

    category = TestHelper.createCategory(categoryRepository);
  }

  @Test
  public void testGetCheeses() throws Exception {
    Cheese cheese = TestHelper.createCheese(category, cheeseRepository);

    mockRequest.perform(get(CheeseController.ENDPOINT)).andExpect(status().isOk())
        // https://github.com/json-path/JsonPath
        .andExpect(jsonPath("$[0].id").value(cheese.getId())) // 0th element id property
        .andExpect(jsonPath("$[0].category.id").value(category.getId()));
  }

  @Test
  public void testCreateCheese() throws Exception {
    assertEquals(0, cheeseRepository.count());

    String postBody = String.format(
        "{ \"name\": \"test cheese\", \"description\": \"test " + "description\", " +
            "\"categoryId\": %d }",
        category.getId()
    );

    mockRequest
        .perform(post(CheeseController.ENDPOINT)
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(postBody))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.category.id").value(category.getId()));

    assertEquals(1, cheeseRepository.count());
  }

  @Test
  public void testDeleteCheese() throws Exception {
    Cheese cheese = TestHelper.createCheese(category, cheeseRepository);

    String endpoint = String.format("%s/%d", CheeseController.ENDPOINT, cheese.getId());

    assertEquals(1, cheeseRepository.count());

    mockRequest.perform(delete(endpoint)).andExpect(status().isAccepted());

    assertEquals(0, cheeseRepository.count());
  }
}
