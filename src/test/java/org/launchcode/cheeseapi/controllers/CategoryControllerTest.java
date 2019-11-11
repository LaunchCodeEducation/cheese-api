package org.launchcode.cheeseapi.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.launchcode.cheeseapi.IntegrationTestConfig;
import org.launchcode.cheeseapi.repositories.CategoryRepository;
import org.launchcode.cheeseapi.repositories.CheeseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTestConfig
@RunWith(SpringRunner.class)
public class CategoryControllerTest {
  private static final String categoriesEndpoint = "/categories";

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private MockMvc mockRequest;

  @Test
  public void testGetCategories() throws Exception {
    mockRequest.perform(get(categoriesEndpoint)).andExpect(status().isOk());
  }

  @Test
  public void testCreateCategory() throws Exception {
    assertEquals(0, categoryRepository.count());

    mockRequest.perform(
        post(categoriesEndpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"name\": \"test\"}")
    ).andExpect(status().isOk());

    assertEquals(1, categoryRepository.count());
  }
}
