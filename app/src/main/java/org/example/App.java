package org.example;

import java.util.Map;

public class App {

  public static void main(String[] args) {
    // No parameters
    System.out.println("Search parameters: " + Map.of());
    System.out.println("Results: " + BestMatchedRestaurants.searchRestaurants(Map.of()));
    System.out.println();

    // Name and customerRating parameters
    Map<String, Object> nameAndCustomerRatingParams = Map.of("name", "DeLiCiOuS", "customerRating", 4);
    System.out.println("Search parameters: " + nameAndCustomerRatingParams);
    System.out.println("Results: " + BestMatchedRestaurants.searchRestaurants(nameAndCustomerRatingParams));
    System.out.println();
  }

}
