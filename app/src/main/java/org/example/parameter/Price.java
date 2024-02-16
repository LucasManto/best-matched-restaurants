package org.example.parameter;

public class Price extends Parameter {

  private int price;

  public Price(Object price) {
    if (!(price instanceof Integer)) {
      throw new IllegalArgumentException("Price must be an integer");
    }
    if ((int) price < 10 || (int) price > 50) {
      throw new IllegalArgumentException("Price must be a value between 10 and 50 inclusive");
    }
    this.price = (int) price;
  }

  @Override
  boolean condition(Object value) {
    return (int) value <= this.price;
  }

}
