package org.launchcode.cheeseapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "menus")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Menu {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  private String description;

  @JsonManagedReference
  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
  @JoinTable(name = "menu_cheeses",
      joinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "cheese_id", referencedColumnName = "id"))
  private List<Cheese> cheeses = new ArrayList<>();

  public void addCheese(Cheese cheese) {
    this.getCheeses().add(cheese);
    cheese.getMenus().add(this);
  }
}
