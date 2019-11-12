package org.launchcode.cheeseapi.models.DTOs;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CheeseDTO extends NameAndDescriptionEntityDTO {
  @NotNull
  @Min(value = 1, message = "invalid category ID")
  private Long categoryId;
}
