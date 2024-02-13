package org.example;

public class Restaurant {
  public String name;
  public int customerRating;
  public int distance;
  public int price;
  public String cuisine;

  public Restaurant(String name, int customerRating, int distance, int price, String cuisine) {
    this.name = name;
    this.customerRating = customerRating;
    this.distance = distance;
    this.price = price;
    this.cuisine = cuisine;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Restaurant other = (Restaurant) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (customerRating != other.customerRating)
      return false;
    if (distance != other.distance)
      return false;
    if (price != other.price)
      return false;
    if (cuisine == null) {
      if (other.cuisine != null)
        return false;
    } else if (!cuisine.equals(other.cuisine))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Restaurant [name=" + name + ", customerRating=" + customerRating + ", distance=" + distance + ", price="
        + price + ", cuisine=" + cuisine + "]";
  }

}
