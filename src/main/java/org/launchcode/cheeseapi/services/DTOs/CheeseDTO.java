package org.launchcode.cheeseapi.services.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.launchcode.cheeseapi.models.Category;
import org.launchcode.cheeseapi.models.Cheese;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CheeseDTO extends NameAndDescriptionEntityDTO implements Convertible<Cheese> {
  @NotNull
  @Min(value = 1, message = "invalid category ID")
  private Long categoryId;

  @Override
  public Cheese convertToEntity() {
    Cheese cheese = new Cheese();
    cheese.setName(this.getName());
    cheese.setDescription(this.getDescription());

    return cheese;
  }

  public Cheese convertToEntity(Category category) {
    Cheese cheese = this.convertToEntity();
    cheese.setCategory(category);

    return cheese;
  }
}
