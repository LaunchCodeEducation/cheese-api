package org.launchcode.cheeseapi.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends NameBase {}
