package org.launchcode.cheeseapi.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.launchcode.cheeseapi.IntegrationTestConfig;
import org.launchcode.cheeseapi.models.Category;
import org.launchcode.cheeseapi.repositories.CategoryRepository;
import org.launchcode.cheeseapi.utils.TestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTestConfig
@RunWith(SpringRunner.class)
public class CategoryControllerTest {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private MockMvc mockRequest;

  @Test
  public void testGetCategories() throws Exception {
    Category category = TestHelper.createCategory(categoryRepository);

    mockRequest
        .perform(get(CategoryController.ENDPOINT))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(category.getId()));
  }

  @Test
  public void testCreateCategory() throws Exception {
    assertEquals(0, categoryRepository.count());

    mockRequest
        .perform(post(CategoryController.ENDPOINT)
                     .contentType(MediaType.APPLICATION_JSON)
                     .content("{ \"name\": \"test\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("test"));

    assertEquals(1, categoryRepository.count());
  }
}
