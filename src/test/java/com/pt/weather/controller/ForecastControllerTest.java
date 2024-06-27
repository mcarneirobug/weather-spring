package com.pt.weather.controller;

import com.pt.weather.exception.advice.GlobalControllerExceptionHandler;

import com.pt.weather.model.dto.WeatherResponse;
import com.pt.weather.service.ForecastService;

import lombok.SneakyThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ForecastControllerTest {

  private static final String RESOURCE_API_FORECAST                    = "/api/forecast/{cityName}";
  private static final String DAYS_PARAM                               = "days";
  private static final String INTERNAL_SERVER_ERROR_TITLE              = "Internal Server Error";
  private static final String INSTANCE_VALUE                           = "/api/forecast/London";
  private static final String DETAIL_EXCEPTION_VALUE                   = "An unexpected error occurred while processing your request. Please try again later.";
  private static final String UNEXPECTED_ERROR                         = "Unexpected error";
  private static final String CITY_NAME                                = "London";
  private static final int    INVALID_DAY                              = 4;
  private static final int    DAY_3_VALID                              = 3;
  private static final int    DAY_5_VALID                              = 5;
  private static final String THE_DAYS_PARAMETER_MUST_BE_EITHER_3_OR_5 = "The 'days' parameter must be either 3 or 5.";

  @Mock
  private ForecastService forecastService;

  @InjectMocks
  private ForecastController forecastController;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(forecastController)
        .setControllerAdvice(new GlobalControllerExceptionHandler()).build();
  }

  @SneakyThrows
  @ParameterizedTest
  @ValueSource(ints = {DAY_3_VALID, DAY_5_VALID})
  @DisplayName("GET Forecast - Valid Days")
  void shouldReturnForecastForValidDays(int days) {
    // given
    final WeatherResponse mockResponse = new WeatherResponse();

    given(forecastService.getWeatherForecast(CITY_NAME, days)).willReturn(mockResponse);

    // then
    mockMvc.perform(get(RESOURCE_API_FORECAST, CITY_NAME)
            .param(DAYS_PARAM, String.valueOf(days))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    // then
    then(forecastService).should().getWeatherForecast(CITY_NAME, days);
  }

  @Test
  @SneakyThrows
  @DisplayName("GET Forecast - Invalid Days Parameter")
  void shouldReturnBadRequestForInvalidDays() {
    // when
    mockMvc.perform(
            get(RESOURCE_API_FORECAST, CITY_NAME).param(DAYS_PARAM, String.valueOf(INVALID_DAY))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.detail").value(THE_DAYS_PARAMETER_MUST_BE_EITHER_3_OR_5))
        .andExpect(jsonPath("$.instance").value(INSTANCE_VALUE));

    // then
    then(forecastService).shouldHaveNoInteractions();
  }

  @Test
  @SneakyThrows
  @DisplayName("GET Forecast - Internal Server Error")
  void shouldHandleUnexpectedException() {
    // given
    String cityName = "London";
    int days = 3;

    given(forecastService.getWeatherForecast(cityName, days)).willThrow(
        new RuntimeException(UNEXPECTED_ERROR));

    // when
    mockMvc.perform(get(RESOURCE_API_FORECAST, cityName).param(DAYS_PARAM, String.valueOf(days))
            .accept(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError())
        .andExpect(jsonPath("$.title").value(INTERNAL_SERVER_ERROR_TITLE))
        .andExpect(jsonPath("$.detail").value(DETAIL_EXCEPTION_VALUE))
        .andExpect(jsonPath("$.instance").value(INSTANCE_VALUE));

    // then
    then(forecastService).should().getWeatherForecast(cityName, days);
  }
}
