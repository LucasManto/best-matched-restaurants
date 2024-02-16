package org.example.parameter;

public class CustomerRating extends Parameter {

  private int customerRating;

  public CustomerRating(Object customerRating) {
    if (!(customerRating instanceof Integer)) {
      throw new IllegalArgumentException("Customer rating must be an integer");
    }
    if ((int) customerRating < 1 || (int) customerRating > 5) {
      throw new IllegalArgumentException("Customer rating must be a value between 1 and 5 inclusive");
    }
    this.customerRating = (int) customerRating;
  }

  @Override
  boolean condition(Object value) {
    return (int) value >= this.customerRating;
  }

}
