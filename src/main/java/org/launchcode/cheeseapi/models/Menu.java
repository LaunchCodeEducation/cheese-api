package org.launchcode.cheeseapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
  @ManyToMany(cascade = CascadeType.ALL)
  /*
   * for 415 "content type not accepted" error
   * requires that that the request body perfectly matches the POJO
   * since only { name: "" } is sent it fails with this completely unhelpful error
   * https://stackoverflow.com/a/34417678
   * */
  //  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private List<Cheese> cheeses = new ArrayList<>();
}
