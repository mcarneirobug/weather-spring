package com.pt.weather.exception;

public class CityNotFoundException extends RuntimeException {
  public CityNotFoundException(String message) {
    super(message);
  }
}
