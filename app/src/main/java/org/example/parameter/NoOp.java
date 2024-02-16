package org.example.parameter;

public class NoOp extends Parameter {

  @Override
  boolean condition(Object value) {
    return true;
  }

}
