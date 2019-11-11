package org.launchcode.cheeseapi.models.DTOs;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MenuCheeseDTO {
  @NotNull
  @Min(value = 1, message = "invalid cheese ID")
  private Long cheeseId;
}
