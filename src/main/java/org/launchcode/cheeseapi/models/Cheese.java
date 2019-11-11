package org.launchcode.cheeseapi.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cheeses")
public class Cheese extends NameBase {}
