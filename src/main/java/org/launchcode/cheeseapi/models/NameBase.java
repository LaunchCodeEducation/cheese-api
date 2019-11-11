package org.launchcode.cheeseapi.models;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@MappedSuperclass
abstract class NameBase extends IdentityBase {
  @Size(min = 3, max = 20, message = "name must be between 3 and 20 characters")
  @Pattern(regexp = "[A-Za-z ]+", message = "name must only contain alphabetic characters")
  private String name;
}
