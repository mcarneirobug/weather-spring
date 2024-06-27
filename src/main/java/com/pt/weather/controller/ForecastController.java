package com.pt.weather.controller;

import com.pt.weather.exception.advice.ProblemDetails;

import com.pt.weather.model.dto.WeatherResponse;

import com.pt.weather.service.ForecastService;

import com.pt.weather.util.ProblemDetailsUtils;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.pt.weather.util.Constants.LogMessage.ERROR_OCCURRED_WHILE_FETCHING_FORECAST_FOR_CITY;
import static com.pt.weather.util.Constants.ProblemDetail.FORECAST_INSTANCE;
import static com.pt.weather.util.Constants.ProblemDetail.THE_DAYS_PARAMETER_MUST_BE_EITHER_3_OR_5;
import static com.pt.weather.util.Constants.Url.Api.RESOURCE_FORECAST;

@RestController
@RequiredArgsConstructor
@RequestMapping(RESOURCE_FORECAST)
public class ForecastController {

  private static final Logger logger                                   = LoggerFactory.getLogger(
      ForecastController.class);

  private final ForecastService forecastService;

  @GetMapping("/{cityName}")
  public ResponseEntity<?> getForecast(@PathVariable String cityName,
      @RequestParam(defaultValue = "3") int days) {

    Optional<ProblemDetails> validationProblem = validateDaysParameter(cityName, days);
    if (validationProblem.isPresent()) {
      return ResponseEntity.badRequest().body(validationProblem.get());
    }

    try {
      WeatherResponse forecast = forecastService.getWeatherForecast(cityName, days);
      return ResponseEntity.ok(forecast);
    } catch (Exception e) {
      logger.error(ERROR_OCCURRED_WHILE_FETCHING_FORECAST_FOR_CITY, cityName, e.getMessage(), e);
      ProblemDetails problemDetails = ProblemDetailsUtils.createInternalServerErrorProblem(
          FORECAST_INSTANCE + cityName);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problemDetails);
    }
  }

  private Optional<ProblemDetails> validateDaysParameter(String cityName, int days) {
    return (days != 3 && days != 5)
        ? Optional.of(ProblemDetailsUtils.createInvalidParameterProblem(
        FORECAST_INSTANCE + cityName,
        THE_DAYS_PARAMETER_MUST_BE_EITHER_3_OR_5
    ))
        : Optional.empty();
  }
}
