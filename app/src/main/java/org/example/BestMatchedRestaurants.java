package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.example.parameter.Parameter;
import org.example.parameter.ParametersFactory;

public class BestMatchedRestaurants {
    private static final String COMMA_DELIMITER = ",";
    private static final String CUISINES_FILE_PATH = "../csv/cuisines.csv";
    private static final String RESTAURANTS_FILE_PATH = "../csv/restaurants.csv";

    private static final int RESULTS_LENGTH = 5;

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

        List<Parameter> parameters = ParametersFactory.build(params);

        return restaurants.stream()
                .filter(restaurant -> parameters.stream().allMatch(param -> param.filter(restaurant)))
                .sorted(BestMatchedRestaurants::byDistanceAndCustomerRatingAndPrice)
                .limit(RESULTS_LENGTH)
                .toList();
    }

    private static int byDistanceAndCustomerRatingAndPrice(Restaurant r1, Restaurant r2) {
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
    }
}
