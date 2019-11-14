package org.launchcode.cheeseapi.services.DTOs;

interface Convertible<Entity> {
  Entity convertToEntity();
}
