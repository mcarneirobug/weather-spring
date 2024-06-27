package com.pt.weather.service.impl;

import com.pt.weather.exception.ApiException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
class CityValidationServiceImplTest {

  private static final String API_URL_VALUE                                    = "http://api.openweathermap.org/data/2.5/";
  private static final String API_URL                                          = "apiUrl";
  private static final String API_KEY                                          = "apiKey";
  private static final String API_KEY_VALUE                                    = "api-test";
  private static final String WEATHER_RESOURCE                                 = "/weather";
  private static final String APPID                                            = "appid";
  private static final String CITY_DETAILS                                     = "q";
  private static final String FAILED_TO_VERIFY_CITY_DUE_TO_AN_UNEXPECTED_ERROR = "Failed to verify city due to an unexpected error";
  private static final String CITY_NAME                                        = "London";
  private static final String UNEXPECTED_ERROR                                 = "Unexpected error";
  private static final String VALID_RESPONSE                                   = "valid response";

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private CityValidationServiceImpl service;

  @BeforeEach
  void setUp() {
    setField(service, API_KEY, API_KEY_VALUE);
    setField(service, API_URL, API_URL_VALUE);
  }

  @Test
  void shouldIsValidCity_whenThrownUnexpectedApiException() {
    // given
    final String expectedUrl = getUriString();

    when(restTemplate.getForObject(expectedUrl, String.class)).thenThrow(
        new RuntimeException(UNEXPECTED_ERROR));

    // then
    assertAll(() -> assertThatExceptionOfType(ApiException.class).isThrownBy(
                () -> service.isValidCity(CITY_NAME))
            .withMessage(FAILED_TO_VERIFY_CITY_DUE_TO_AN_UNEXPECTED_ERROR),
        () -> verify(restTemplate).getForObject(expectedUrl, String.class));
  }

  @Test
  void shouldIsValidCity_whenValidCity_thenReturnsTrue() {
    // given
    final String expectedUrl = getUriString();

    when(restTemplate.getForObject(expectedUrl, String.class)).thenReturn(VALID_RESPONSE);

    // when
    boolean result = service.isValidCity(CITY_NAME);

    // then
    assertAll(() -> assertTrue(result),
        () -> verify(restTemplate).getForObject(expectedUrl, String.class));
  }

  @Test
  void shouldIsValidCity_whenInvalidCity_thenReturnsFalse() {
    // given
    String expectedUrl = getUriString();

    when(restTemplate.getForObject(expectedUrl, String.class)).thenThrow(
        HttpClientErrorException.NotFound.class);

    // when
    boolean result = service.isValidCity(CITY_NAME);

    // then
    assertAll(() -> assertFalse(result),
        () -> verify(restTemplate).getForObject(expectedUrl, String.class));
  }

  private static String getUriString() {
    return UriComponentsBuilder.fromHttpUrl(API_URL_VALUE).path(WEATHER_RESOURCE)
        .queryParam(CITY_DETAILS, CITY_NAME).queryParam(APPID, API_KEY_VALUE).toUriString();
  }
}
