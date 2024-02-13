package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BestMatchedRestaurants {
    private static String COMMA_DELIMITER = ",";
    private static String CUISINES_FILE_PATH = "../csv/cuisines.csv";
    private static String RESTAURANTS_FILE_PATH = "../csv/restaurants.csv";

    public static List<Restaurant> searchRestaurants(Map<String, Object> params) {
        // Load data
        List<String> cuisines = new ArrayList<>();
        List<Restaurant> restaurants = new ArrayList<>();

        try (BufferedReader cuisinesReader = new BufferedReader(new FileReader(CUISINES_FILE_PATH));
                BufferedReader restaurantsReader = new BufferedReader(new FileReader(RESTAURANTS_FILE_PATH))) {
            String line = cuisinesReader.readLine();
            while ((line = cuisinesReader.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                cuisines.add(values[1]);
            }

            line = restaurantsReader.readLine();
            while ((line = restaurantsReader.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                String name = values[0];
                int customerRating = Integer.parseInt(values[1]);
                int distance = Integer.parseInt(values[2]);
                int price = Integer.parseInt(values[3]);
                String cuisine = cuisines.get(Integer.parseInt(values[4]) - 1);
                restaurants.add(new Restaurant(name, customerRating, distance, price, cuisine));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }

        validateParams(params);

        // Filter and sort results
        return restaurants.stream()
                .filter(restaurant -> {
                    if (params == null) {
                        return true;
                    }

                    if (params.containsKey("name")) {
                        String name = (String) params.get("name");
                        return restaurant.name.toUpperCase().contains(name.toUpperCase());
                    }

                    return true;
                })
                .filter(restaurant -> {
                    if (params == null) {
                        return true;
                    }

                    if (params.containsKey("customerRating")) {
                        int customerRating = (int) params.get("customerRating");
                        return restaurant.customerRating >= customerRating;
                    }

                    return true;
                })
                .filter(restaurant -> {
                    if (params == null) {
                        return true;
                    }

                    if (params.containsKey("distance")) {
                        int distance = (int) params.get("distance");
                        return restaurant.distance <= distance;
                    }

                    return true;
                })
                .filter(restaurant -> {
                    if (params == null) {
                        return true;
                    }

                    if (params.containsKey("price")) {
                        int price = (int) params.get("price");
                        return restaurant.price <= price;
                    }

                    return true;
                })
                .filter(restaurant -> {
                    if (params == null) {
                        return true;
                    }

                    if (params.containsKey("cuisine")) {
                        String cuisine = (String) params.get("cuisine");
                        return restaurant.cuisine.toUpperCase().contains(cuisine.toUpperCase());
                    }

                    return true;
                })
                .sorted((r1, r2) -> {
                    if (r1.distance != r2.distance) {
                        return r1.distance - r2.distance;
                    }

                    if (r1.customerRating != r2.customerRating) {
                        return r2.customerRating - r1.customerRating;
                    }

                    if (r1.price != r2.price) {
                        return r1.price - r2.price;
                    }

                    return 0;
                })
                .limit(5)
                .toList();
    }

    private static void validateParams(Map<String, Object> params) throws IllegalArgumentException {
        if (params == null) {
            return;
        }

        // Name
        if (params.containsKey("name") && !(params.get("name") instanceof String)) {
            throw new IllegalArgumentException("name must be a String");
        }

        // Customer rating
        if (params.containsKey("customerRating") && !(params.get("customerRating") instanceof Integer)) {
            throw new IllegalArgumentException("customerRating must be an Integer");
        }
        if (params.containsKey("customerRating")
                && ((int) params.get("customerRating") < 1 || (int) params.get("customerRating") > 5)) {
            throw new IllegalArgumentException("customerRating must be a value between 1 and 5 inclusive");
        }

        // Distance
        if (params.containsKey("distance") && !(params.get("distance") instanceof Integer)) {
            throw new IllegalArgumentException("distance must be an Integer");
        }
        if (params.containsKey("distance")
                && ((int) params.get("distance") < 1 || (int) params.get("distance") > 10)) {
            throw new IllegalArgumentException("distance must be a value between 1 and 5 inclusive");
        }

        // Price
        if (params.containsKey("price") && !(params.get("price") instanceof Integer)) {
            throw new IllegalArgumentException("price must be a Integer");
        }
        if (params.containsKey("price")
                && ((int) params.get("price") < 10 || (int) params.get("price") > 50)) {
            throw new IllegalArgumentException("price must be a value between 10 and 50 inclusive");
        }

        // Cuisine
        if (params.containsKey("cuisine") && !(params.get("cuisine") instanceof String)) {
            throw new IllegalArgumentException("cuisine must be a String");
        }
    }
}
