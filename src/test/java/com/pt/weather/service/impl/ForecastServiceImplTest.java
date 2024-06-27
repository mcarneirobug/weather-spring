package com.pt.weather.service.impl;

import com.pt.weather.model.dto.WeatherResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.web.client.RestTemplate;

import org.springframework.web.util.UriComponentsBuilder;

import static com.pt.weather.util.Constants.Url.QueryParam.CITY_DETAILS;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
class ForecastServiceImplTest {

  private static final String API_URL_VALUE     = "http://api.openweathermap.org/data/2.5/";
  private static final String API_URL           = "apiUrl";
  private static final String API_KEY           = "apiKey";
  private static final String API_KEY_VALUE     = "api-test";
  private static final String CITY_NAME         = "London";
  private static final String FORECAST_RESOURCE = "/forecast";
  private static final String APPID             = "appid";
  private static final String CNT_TIMESTAMP     = "cnt";
  private static final int    DAY_3             = 3;

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private ForecastServiceImpl service;

  @BeforeEach
  void setUp() {
    setField(service, API_KEY, API_KEY_VALUE);
    setField(service, API_URL, API_URL_VALUE);
  }

  @Test
  void shouldGetWeatherForecast_whenValidCityAndDaysIsValid_thenReturnsWeatherResponse() {
    // given
    final String expectedUrl = getUriString();
    final WeatherResponse weatherResponseExpected = new WeatherResponse();

    when(restTemplate.getForObject(expectedUrl, WeatherResponse.class)).thenReturn(weatherResponseExpected);

    // when
    final WeatherResponse result = service.getWeatherForecast(CITY_NAME, DAY_3);

    // then
    assertAll(
        () -> assertEquals(weatherResponseExpected, result),
        () -> verify(restTemplate).getForObject(expectedUrl, WeatherResponse.class));
  }

  private static String getUriString() {
    return UriComponentsBuilder.fromHttpUrl(API_URL_VALUE).path(FORECAST_RESOURCE)
        .queryParam(CITY_DETAILS, CITY_NAME).queryParam(CNT_TIMESTAMP, DAY_3 * 8)
        .queryParam(APPID, API_KEY_VALUE).toUriString();
  }
}
