package com.pt.weather.service.impl;

import com.pt.weather.exception.CityNotFoundException;

import com.pt.weather.exception.InvalidCityException;
import com.pt.weather.model.City;
import com.pt.weather.model.dto.CityResponse;

import com.pt.weather.repository.CityRepository;

import com.pt.weather.service.CityValidationService;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

  private static final long   ID_1                        = 1L;
  private static final String LONDON                      = "London";
  private static final long   ID_2                        = 2L;
  private static final String NEW_YORK                    = "New York";
  private static final long   ID_3                        = 3L;
  private static final String PARIS                       = "Paris";
  private static final String COULD_NOT_FIND_CITY_WITH_ID = "Could not find city with id: %d";
  private static final String CITY_IS_NOT_VALID_TO_CREATE = "City is not valid to create.";

  @Mock
  private CityValidationService validationService;

  @Mock
  private CityRepository cityRepository;

  @InjectMocks
  private CityServiceImpl cityService;

  @Nested
  class CreateCityTests {

    @Test
    void shouldCreateCity_whenCityNameIsInvalid_thenThrowsException() {
      // given
      when(validationService.isValidCity(LONDON)).thenReturn(false);

      // then
      assertAll(() -> assertThatExceptionOfType(InvalidCityException.class).isThrownBy(
              () -> cityService.createCity(LONDON)).withMessage(CITY_IS_NOT_VALID_TO_CREATE),
          () -> verify(validationService).isValidCity(LONDON));
    }

    @Test
    void shouldCreateCity_whenNameIsValid_thenReturnCityResponse() {
      // given
      final var city = getCity();
      final var expectedCityResponse = getCityResponse();

      when(validationService.isValidCity(PARIS)).thenReturn(true);
      when(cityRepository.save(any(City.class))).thenReturn(city);

      // when
      final var actualResponse = cityService.createCity(PARIS);

      assertAll(
          () -> assertEquals(expectedCityResponse.name(), actualResponse.name()),
          () -> verify(validationService).isValidCity(PARIS)
      );
    }
  }

  @Nested
  class FindAllCitiesTests {
    @Test
    void shouldGetAllCities_whenMultipleCitiesIsAvailable_thenReturnListOfCities() {
      // given
      final var expectedResponses = getCitiesResponse();

      when(cityRepository.findAll()).thenReturn(getCities());

      // when
      final List<CityResponse> actualResponses = cityService.getAllCities();

      // then
      assertAll(() -> assertEquals(expectedResponses, actualResponses),
          () -> verify(cityRepository).findAll());
    }

    @Test
    void shouldGetAllCities_whenNoCitiesIsAvailable_thenReturnListEmpty() {
      // given
      List<CityResponse> expectedResponses = List.of();

      when(cityRepository.findAll()).thenReturn(Collections.emptyList());

      // when
      final List<CityResponse> actualResponses = cityService.getAllCities();

      // then
      assertAll(() -> assertEquals(expectedResponses, actualResponses),
          () -> verify(cityRepository).findAll());
    }
  }

  @Nested
  class FindCityByIdTests {

    @Test
    void shouldGetCityById_whenIdIsValid_thenReturnCityResponse() {
      // given
      final var expectedCityResponse = getCityResponse();

      when(cityRepository.findById(ID_1)).thenReturn(Optional.of(getCity()));

      // then
      final var actualResponse = cityService.getCityById(ID_1);

      assertAll(() -> assertEquals(expectedCityResponse, actualResponse),
          () -> verify(cityRepository).findById(ID_1));
    }

    @Test
    void shouldGetCityById_whenIdIsInvalid_thenThrowsException() {
      // given
      when(cityRepository.findById(ID_1)).thenReturn(Optional.empty());

      // then
      assertAll(() -> assertThatExceptionOfType(CityNotFoundException.class).isThrownBy(
                  () -> cityService.getCityById(ID_1))
              .withMessage(String.format(COULD_NOT_FIND_CITY_WITH_ID, ID_1)),
          () -> verify(cityRepository).findById(ID_1));
    }
  }

  private static List<City> getCities() {
    return List.of(new City(ID_1, LONDON), new City(ID_2, NEW_YORK));
  }

  private static List<CityResponse> getCitiesResponse() {
    return List.of(new CityResponse(ID_1, LONDON), new CityResponse(ID_2, NEW_YORK));
  }

  private static City getCity() {
    return City.builder().id(ID_3).name(PARIS).build();
  }

  private static CityResponse getCityResponse() {
    return new CityResponse(ID_3, PARIS);
  }
}
