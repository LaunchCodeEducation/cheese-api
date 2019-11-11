package org.launchcode.cheeseapi.models;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
abstract class IdentityBase {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
}