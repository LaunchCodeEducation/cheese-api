package org.launchcode.cheeseapi.models.DTOs;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
abstract class NameEntityDTO {
  @NotNull
  @Size(min = 3, max = 20, message = "name must be between 3 and 20 characters")
  @Pattern(regexp = "[A-Za-z ]+", message = "name must only contain alphabetic characters")
  private String name;
}
