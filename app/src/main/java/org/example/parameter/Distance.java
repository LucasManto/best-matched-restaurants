package org.example.parameter;

public class Distance extends Parameter {

  private int distance;

  public Distance(Object distance) {
    if (!(distance instanceof Integer)) {
      throw new IllegalArgumentException("Distance must be an integer");
    }
    if ((int) distance < 1 || (int) distance > 10) {
      throw new IllegalArgumentException("Distance must be a value between 1 and 10 inclusive");
    }
    this.distance = (int) distance;
  }

  @Override
  boolean condition(Object value) {
    return (int) value <= this.distance;
  }

}
