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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "cheeses")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Cheese {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  private String description;

  @JsonBackReference
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private Category category;

  @JsonBackReference
  @ManyToMany(mappedBy = "cheeses", fetch = FetchType.EAGER)
  private List<Menu> menus = new ArrayList<>();
}
