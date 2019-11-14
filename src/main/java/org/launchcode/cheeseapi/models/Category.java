package org.launchcode.cheeseapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "categories")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  @JsonBackReference
  @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
  private List<Cheese> cheeses = new ArrayList<>(); // prevent NPE when not yet fetched

  public void addCheese(Cheese cheese) {
    this.getCheeses().add(cheese);
  }
}
