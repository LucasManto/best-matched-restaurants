package org.example.parameter;

public abstract class Parameter {

  public boolean filter(Object object) {
    try {
      String fieldName = decapitalizeString(this.getClass().getSimpleName());
      Object value = object.getClass().getField(fieldName).get(object);
      return condition(value);
    } catch (Exception e) {
      // Return true beacause when an error occurs we don't want to filter anything
      return true;
    }
  }

  abstract boolean condition(Object value);

  private static String decapitalizeString(String string) {
    return string == null || string.isEmpty() ? "" : Character.toLowerCase(string.charAt(0)) + string.substring(1);
  }
}
