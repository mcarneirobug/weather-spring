package com.pt.weather.model.dto;

import com.pt.weather.model.City;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CityResponseTest {

  private static final String NEW_YORK     = "New York";
  private static final long   ID           = 1L;
  private static final String UNKNOWN_NAME = "Unknown";

  @Test
  void shouldToDto_whenValidCity_thenReturnsCityResponse() {
    // given
    final City city = new City(ID, NEW_YORK);
    final CityResponse response = CityResponse.toDto(city);

    // then
    assertAll(() -> assertNotNull(response), () -> assertEquals(ID, response.id()),
        () -> assertEquals(NEW_YORK, response.name()));
  }

  @Test
  void shouldToDto_whenNullCity_ThenReturnsNull() {
    // then
    assertNull(CityResponse.toDto(null));
  }

  @Test
  void shouldWithDefaults_ReturnsDefaultCityResponse() {
    // given
    final CityResponse defaultResponse = CityResponse.withDefaults();

    // then
    assertAll(() -> assertNull(defaultResponse.id()),
        () -> assertEquals(UNKNOWN_NAME, defaultResponse.name()));
  }
}
