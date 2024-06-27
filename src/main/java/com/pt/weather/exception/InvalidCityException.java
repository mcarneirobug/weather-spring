package com.pt.weather.exception;

public class InvalidCityException extends RuntimeException {
  public InvalidCityException(String message) {
    super(message);
  }
}
