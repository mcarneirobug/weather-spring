package com.pt.weather.util;

import com.pt.weather.exception.advice.ProblemDetails;

import lombok.experimental.UtilityClass;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static com.pt.weather.util.Constants.ProblemDetail.ERROR_OCCURRED_WHILE_PROCESSING_YOUR_REQUEST;
import static com.pt.weather.util.Constants.ProblemDetail.INTERNAL_SERVER_ERROR_TITLE;
import static com.pt.weather.util.Constants.ProblemDetail.INVALID_REQUEST_PARAMETER_TITLE;

@UtilityClass
public class ProblemDetailsUtils {

  public static ProblemDetails createInternalServerErrorProblem(String instance) {
    return new ProblemDetails(INTERNAL_SERVER_ERROR_TITLE, HttpStatus.INTERNAL_SERVER_ERROR.value(),
        ERROR_OCCURRED_WHILE_PROCESSING_YOUR_REQUEST, instance, LocalDateTime.now());
  }

  public static ProblemDetails createInvalidParameterProblem(String instance, String detail) {
    return new ProblemDetails(INVALID_REQUEST_PARAMETER_TITLE, HttpStatus.BAD_REQUEST.value(),
        detail, instance, LocalDateTime.now());
  }
}
