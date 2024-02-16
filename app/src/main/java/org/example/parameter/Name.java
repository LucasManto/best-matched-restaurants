package org.example.parameter;

public class Name extends Parameter {

  private String name;

  public Name(Object name) {
    if (!(name instanceof String)) {
      throw new IllegalArgumentException("Name must be a string");
    }

    this.name = (String) name;
  }

  @Override
  public boolean condition(Object value) {
    return value.toString().toUpperCase().contains(name.toUpperCase());
  }

}
