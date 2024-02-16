package org.example.parameter;

public class Cuisine extends Parameter {

  private String cuisine;

  public Cuisine(Object cuisine) {
    if (!(cuisine instanceof String)) {
      throw new IllegalArgumentException("Cuisine must be a string");
    }
    this.cuisine = (String) cuisine;
  }

  @Override
  boolean condition(Object value) {
    return value.toString().toUpperCase().contains(cuisine.toUpperCase());
  }

}
