package org.launchcode.cheeseapi.models;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

@Data // automates boilerplate generation
@Entity // registers POJO as a persisted Entity to be managed in Spring context
public class Cheese {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Size(min = 3, max = 20, message = "name must be between 3 and 20 characters")
  @Pattern(regexp = "[A-Za-z ]+", message = "name must only contain alphabetic characters")
  private String name;
}
