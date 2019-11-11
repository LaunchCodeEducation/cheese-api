package org.launchcode.cheeseapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;


@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "cheeses")
public class Cheese {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Size(min = 3, max = 20, message = "name must be between 3 and 20 characters")
  @Pattern(regexp = "[A-Za-z ]+", message = "name must only contain alphabetic characters")
  private String name;

  @JsonBackReference
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private Category category;

  @Transient
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private long categoryId;
}
