# Best matched restaurants

You have data about local restaurants located near your company, which you can find in the **restaurants.csv** file. You would like to develop a basic search function that allows your colleagues to search those restaurants to help them find where they would like to have lunch. The search is based on five criteria: **Restaurant Name, Customer Rating(1 star ~ 5 stars), Distance(1 mile ~ 10 miles), Price(how much one person will spend on average, $10 ~ $50), Cuisine(Chinese, American, Thai, etc.).** The requirements are listed below.

1. The function should allow users to provide up to five parameters based on the criteria listed above. _You can assume each parameter can contain only one value._
2. If parameter values are invalid, return an error message.
3. The function should return up to five matches based on the provided criteria. If no matches are found, return an empty list. If less than 5 matches are found, return them all. If more than 5 matches are found, return the best 5 matches. The returned results should be sorted according to the rules explained below. Every record in the search results should at least contain the restaurant name.
4. “Best match” is defined as below:
   - A Restaurant Name match is defined as an exact or partial String match with what users provided. For example, “Mcd” would match “Mcdonald’s”.
   - A Customer Rating match is defined as a Customer Rating equal to or more than what users have asked for. For example, “3” would match all the 3 stars restaurants plus all the 4 stars and 5 stars restaurants.
   - A Distance match is defined as a Distance equal to or less than what users have asked for. For example, “2” would match any distance that is equal to or less than 2 miles from your company.
   - A Price match is defined as a Price equal to or less than what users have asked for. For example, “15” would match any price that is equal to or less than $15 per person.
   - A Cuisine match is defined as an exact or partial String match with what users provided. For example, “Chi” would match “Chinese”. You can find all the possible Cuisines in the **cuisines.csv** file. _You can assume each restaurant offers only one cuisine._
   - The five parameters are holding an “AND” relationship. For example, if users provide Name = “Mcdonald’s” and Distance = 2, you should find all “Mcdonald’s” within 2 miles.
   - When multiple matches are found, you should sort them as described below.
     - Sort the restaurants by Distance first.
     - After the above process, if two matches are still equal, then the restaurant with a higher customer rating wins.
     - After the above process, if two matches are still equal, then the restaurant with a lower price wins.
     - After the above process, if two matches are still equal, then you can randomly decide the order.
     - Example: if the input is Customer Rating = 3 and Price = 15. Mcdonald’s is 4 stars with an average spend = $10, and it is 1 mile away. And KFC is 3 stars with an average spend = $8, and it is 1 mile away. Then we should consider Mcdonald’s as a better match than KFC. (They both matches the search criteria -> we compare distance -> we get a tie -> we then compare customer rating -> Mcdonald’s wins)

## Solution description

This is the improvement of the first working solution for the **best-matched-restaurants** problem. The focus in this phase was **enhancing maintainability and readability**. This was achieved by creating a `Parameter` class with **base behavior and definitions**, and **concrete implementations of each accepted parameter**. The concrete implementation objects are created by `ParametersFactory` based on user input.

Now to accept a new parameter there is no need to modify `BestMatchedRestaurants`. It is enough to create a new concrete implementation and add it to the `ParametersFactory`. And if there is the need to **change the behavior** of an existing parameter, the change is scoped inside the **concrete implementation only**.

Also the sorting algorithm was separated into a named function called `byDistanceAndCustomerRatingAndPrice` that makes the sorting criteria easier to understand.

It uses the data provided and by executing the project (see [How to run](#how-to-run)) you would get the result of searching for the best-matched restaurants **without defining any parameter** and the result of defining **name=DeLiCiOuS AND customerRating=4**. The result is a restaurant list that shows **every property** of each restaurant, which includes its **cuisine name**.

If you want to evaluate the result of the search by providing some parameters, you can change the code inside the **main** function located in the **src/main/java/org/example/App.java** file.

## Assumptions and explanations

- This version uses only data from the provided CSV files/
- Search by string (name and cuisine) are **case-insensitive**, to achieve **more matches**.
- Price is defined as int to **simplify data manipulation** since the provided records do not present decimal values. But in a scenario where it should be of a monetary type the **best practice would be to use Money and Currency API**.
- Code for `Restaurant.equals` was **generated automatically** with the objective to **compare restaurants in tests** in a clean way.

## Next steps for enhancing project

- Load data at startup and not for every request.
- Setting data source for `BestMatchedRestaurants` through dependency injection, enabling usage of different sources (File, Memory, Database, etc).
- Build different clients.
  - CLI.
  - Web API.
- Build some UI.

### Technologies

- Java 17
- Gradle 8.6
- JUnit

### How to run

With Java 17 installed, you can run the application with the following command:

```
./gradlew run
```

It will print in the console the following content:

```
Search parameters: {}
Results: [Restaurant [name=Deliciousgenix, customerRating=4, distance=1, price=10, cuisine=Spanish], Restaurant [name=Deliciouszilla, customerRating=4, distance=1, price=15, cuisine=Chinese], Restaurant [name=Fodder Table, customerRating=4, distance=1, price=20, cuisine=Korean], Restaurant [name=Dished Grill, customerRating=3, distance=1, price=10, cuisine=Korean], Restaurant [name=Sizzle Yummy, customerRating=3, distance=1, price=15, cuisine=Russian]]

Search parameters: {name=DeLiCiOuS, customerRating=4}
Results: [Restaurant [name=Deliciousgenix, customerRating=4, distance=1, price=10, cuisine=Spanish], Restaurant [name=Deliciouszilla, customerRating=4, distance=1, price=15, cuisine=Chinese], Restaurant [name=Bang Delicious, customerRating=5, distance=2, price=15, cuisine=Russian], Restaurant [name=Crisp Delicious, customerRating=5, distance=2, price=45, cuisine=Russian], Restaurant [name=Gusto Delicious, customerRating=5, distance=3, price=50, cuisine=Chinese]]
```

You can also run the tests with the following command:

```
./gradlew test
```
