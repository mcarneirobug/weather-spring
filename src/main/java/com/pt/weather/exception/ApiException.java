package com.pt.weather.exception;

public class ApiException extends RuntimeException {
  public ApiException(String message, Throwable cause) {
    super(message, cause);
  }
}
