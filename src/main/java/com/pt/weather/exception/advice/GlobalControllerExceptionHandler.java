package com.pt.weather.exception.advice;

import com.pt.weather.exception.ApiException;

import com.pt.weather.exception.CityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

import static com.pt.weather.util.Constants.GlobalException.*;
import static com.pt.weather.util.Constants.LogMessage.ERROR_OCCURRED_PROCESSING_REQUEST;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ProblemDetails> handleGlobalException(Exception ex, WebRequest request) {
    logError(ex, request);
    ProblemDetails problemDetails = new ProblemDetails(INTERNAL_SERVER_ERROR_TITLE,
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        AN_UNEXPECTED_ERROR_OCCURRED_PLEASE_TRY_AGAIN_LATER,
        request.getDescription(false),
        LocalDateTime.now()
    );
    return new ResponseEntity<>(problemDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ProblemDetails> handleApiException(ApiException ex, WebRequest request) {
    logError(ex, request);
    ProblemDetails problemDetails = new ProblemDetails(BAD_REQUEST_TITLE,
        HttpStatus.BAD_REQUEST.value(),
        ex.getMessage(),
        request.getDescription(false),
        LocalDateTime.now()
    );
    return new ResponseEntity<>(problemDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CityNotFoundException.class)
  public ResponseEntity<ProblemDetails> handleCityNotFoundException(CityNotFoundException ex, WebRequest request) {
    logError(ex, request);

    ProblemDetails problemDetails = new ProblemDetails(NOT_FOUND_TITLE,
        HttpStatus.NOT_FOUND.value(),
        ex.getMessage(),
        request.getDescription(false),
        LocalDateTime.now()
    );
    return new ResponseEntity<>(problemDetails, HttpStatus.NOT_FOUND);
  }

  private void logError(Exception ex, WebRequest request) {
    if (logger.isErrorEnabled()) {
      String requestDescription = request.getDescription(false);
      logger.error(ERROR_OCCURRED_PROCESSING_REQUEST, requestDescription, ex.getMessage(), ex);
    }
  }
}
