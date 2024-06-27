package com.pt.weather.service.impl;

import com.pt.weather.exception.CityNotFoundException;
import com.pt.weather.exception.InvalidCityException;

import com.pt.weather.model.City;

import com.pt.weather.model.dto.CityResponse;
import com.pt.weather.repository.CityRepository;

import com.pt.weather.service.CityService;

import com.pt.weather.service.CityValidationService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

import static com.pt.weather.util.Constants.ExceptionMessage.CITY_IS_NOT_VALID_TO_CREATE;
import static com.pt.weather.util.Constants.ExceptionMessage.COULD_NOT_FIND_CITY_WITH_ID;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

  private final CityValidationService validationService;
  private final CityRepository        cityRepository;

  @Override
  public CityResponse createCity(final String cityName) {

    if (validationService.isValidCity(cityName)) {
      final var cityEntity = buildCity(cityName);

      final var citySaved = cityRepository.save(cityEntity);

      return CityResponse.toDto(citySaved);
    } else {
      throw new InvalidCityException(CITY_IS_NOT_VALID_TO_CREATE);
    }
  }

  @Override
  public List<CityResponse> getAllCities() {
    return cityRepository.findAll().stream()
        .map(CityResponse::toDto)
        .toList();
  }

  @Override
  public CityResponse getCityById(Long id) {
    return cityRepository.findById(id)
        .map(CityResponse::toDto)
        .orElseThrow(() -> new CityNotFoundException(String.format(COULD_NOT_FIND_CITY_WITH_ID, id)));
  }

  private City buildCity(String cityName) {
    return City.builder()
        .name(cityName)
        .build();
  }
}
