package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

class BestMatchedRestaurantsTest {
    @Test
    void shouldReturnRestaurants() {
        List<Restaurant> expected = List.of(
                new Restaurant("Deliciousgenix", 4, 1, 10, "Spanish"),
                new Restaurant("Deliciouszilla", 4, 1, 15, "Chinese"),
                new Restaurant("Fodder Table", 4, 1, 20, "Korean"),
                new Restaurant("Dished Grill", 3, 1, 10, "Korean"),
                new Restaurant("Sizzle Yummy", 3, 1, 15, "Russian"));

        List<Restaurant> actual = BestMatchedRestaurants.searchRestaurants(null);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldReturnRestaurantsWhenInvalidFilterIsSet() {
        List<Restaurant> expected = List.of(
                new Restaurant("Deliciousgenix", 4, 1, 10, "Spanish"),
                new Restaurant("Deliciouszilla", 4, 1, 15, "Chinese"),
                new Restaurant("Fodder Table", 4, 1, 20, "Korean"),
                new Restaurant("Dished Grill", 3, 1, 10, "Korean"),
                new Restaurant("Sizzle Yummy", 3, 1, 15, "Russian"));

        List<Restaurant> actual = BestMatchedRestaurants.searchRestaurants(Map.of("invalid", "invalid"));

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldReturnRestaurantsThatMatchesName() {
        List<Restaurant> expected = List.of(
                new Restaurant("Deliciousgenix", 4, 1, 10, "Spanish"),
                new Restaurant("Deliciouszilla", 4, 1, 15, "Chinese"),
                new Restaurant("Havana Delicious", 3, 1, 35, "Korean"),
                new Restaurant("Bang Delicious", 5, 2, 15, "Russian"),
                new Restaurant("Crisp Delicious", 5, 2, 45, "Russian"));
        Map<String, Object> params = Map.of("name", "DeLiCiOuS");

        List<Restaurant> actual = BestMatchedRestaurants.searchRestaurants(params);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldReturnRestaurantsWithCustomerRatingEqualToOrAbove4() {
        List<Restaurant> expected = List.of(
                new Restaurant("Deliciousgenix", 4, 1, 10, "Spanish"),
                new Restaurant("Deliciouszilla", 4, 1, 15, "Chinese"),
                new Restaurant("Fodder Table", 4, 1, 20, "Korean"),
                new Restaurant("Grove Table", 5, 2, 10, "Mexican"),
                new Restaurant("Bang Delicious", 5, 2, 15, "Russian"));
        Map<String, Object> params = Map.of("customerRating", 4);

        List<Restaurant> actual = BestMatchedRestaurants.searchRestaurants(params);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldReturnRestaurantsWithPriceEqualToOrBelow10() {
        List<Restaurant> expected = List.of(
                new Restaurant("Deliciousgenix", 4, 1, 10, "Spanish"),
                new Restaurant("Dished Grill", 3, 1, 10, "Korean"),
                new Restaurant("Kitchenster", 2, 1, 10, "American"),
                new Restaurant("Chow Table", 1, 1, 10, "Chinese"),
                new Restaurant("Grove Table", 5, 2, 10, "Mexican"));
        Map<String, Object> params = Map.of("price", 10);

        List<Restaurant> actual = BestMatchedRestaurants.searchRestaurants(params);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldReturnRestaurantsWithPriceEqualToOrBelow10AndCustomerRatingEqualToOrAbove5() {
        List<Restaurant> expected = List.of(
                new Restaurant("Grove Table", 5, 2, 10, "Mexican"),
                new Restaurant("Chop Grill", 5, 8, 10, "Indonesian"),
                new Restaurant("Tasteful Grill", 5, 9, 10, "Chinese"),
                new Restaurant("Aroma Chow", 5, 10, 10, "Russian"));
        Map<String, Object> params = Map.of(
                "price", 10,
                "customerRating", 5);

        List<Restaurant> actual = BestMatchedRestaurants.searchRestaurants(params);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldReturnRestaurantsWithCustomerRatingEqualToOrAbove5AndDistanceEqualToOrLessThan1() {
        List<Restaurant> expected = List.of();
        Map<String, Object> params = Map.of(
                "customerRating", 5,
                "distance", 1);

        List<Restaurant> actual = BestMatchedRestaurants.searchRestaurants(params);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldReturnRestaurantsThatMatchesCuisine() {
        List<Restaurant> expected = List.of(
                new Restaurant("Wish Chow", 3, 1, 40, "American"),
                new Restaurant("Kitchenster", 2, 1, 10, "American"),
                new Restaurant("Grove Table", 5, 2, 10, "Mexican"),
                new Restaurant("Presto Grill", 5, 2, 40, "African"),
                new Restaurant("Tablebes", 4, 2, 40, "Mexican"));
        Map<String, Object> params = Map.of(
                "cuisine", "IcAn");

        List<Restaurant> actual = BestMatchedRestaurants.searchRestaurants(params);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldThrowWhenNameIsNotString() {
        Map<String, Object> params = Map.of("name", 123);

        assertThrows(IllegalArgumentException.class, () -> BestMatchedRestaurants.searchRestaurants(params));
    }

    @Test
    void shouldThrowWhenCustomerRatingIsNotInteger() {
        Map<String, Object> params = Map.of("customerRating", "123");

        assertThrows(IllegalArgumentException.class, () -> BestMatchedRestaurants.searchRestaurants(params));
    }

    @Test
    void shouldThrowWhenCustomerRatingIsBelow1() {
        Map<String, Object> params = Map.of("customerRating", 0);

        assertThrows(IllegalArgumentException.class, () -> BestMatchedRestaurants.searchRestaurants(params));
    }

    @Test
    void shouldThrowWhenCustomerRatingIsAbove5() {
        Map<String, Object> params = Map.of("customerRating", 6);

        assertThrows(IllegalArgumentException.class, () -> BestMatchedRestaurants.searchRestaurants(params));
    }

    @Test
    void shouldThrowWhenDistanceIsNotInteger() {
        Map<String, Object> params = Map.of("distance", "123");

        assertThrows(IllegalArgumentException.class, () -> BestMatchedRestaurants.searchRestaurants(params));
    }

    @Test
    void shouldThrowWhenDistanceIsBelow1() {
        Map<String, Object> params = Map.of("distance", 0);

        assertThrows(IllegalArgumentException.class, () -> BestMatchedRestaurants.searchRestaurants(params));
    }

    @Test
    void shouldThrowWhenDistanceIsAbove10() {
        Map<String, Object> params = Map.of("distance", 11);

        assertThrows(IllegalArgumentException.class, () -> BestMatchedRestaurants.searchRestaurants(params));
    }

    @Test
    void shouldThrowWhenPriceIsNotBigDecimal() {
        Map<String, Object> params = Map.of("price", "123");

        assertThrows(IllegalArgumentException.class, () -> BestMatchedRestaurants.searchRestaurants(params));
    }

    @Test
    void shouldThrowWhenPriceIsBelow10() {
        Map<String, Object> params = Map.of("price", 9);

        assertThrows(IllegalArgumentException.class, () -> BestMatchedRestaurants.searchRestaurants(params));
    }

    @Test
    void shouldThrowWhenPriceIsAbove50() {
        Map<String, Object> params = Map.of("price", 51);

        assertThrows(IllegalArgumentException.class, () -> BestMatchedRestaurants.searchRestaurants(params));
    }

    @Test
    void shouldThrowWhenCuisineIsNotString() {
        Map<String, Object> params = Map.of("cuisine", 123);

        assertThrows(IllegalArgumentException.class, () -> BestMatchedRestaurants.searchRestaurants(params));
    }
}
