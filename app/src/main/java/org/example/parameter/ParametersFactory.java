package org.example.parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParametersFactory {

  public static List<Parameter> build(Map<String, Object> params) {
    List<Parameter> parameters = new ArrayList<>();
    if (params == null) {
      return parameters;
    }
    for (String name : params.keySet()) {
      parameters.add(create(name, params.get(name)));
    }
    return parameters;
  }

  private static Parameter create(String name, Object value) {
    switch (name) {
      case "name":
        return new Name(value);
      case "customerRating":
        return new CustomerRating(value);
      case "distance":
        return new Distance(value);
      case "price":
        return new Price(value);
      case "cuisine":
        return new Cuisine(value);
    }
    return new NoOp();
  }

}
