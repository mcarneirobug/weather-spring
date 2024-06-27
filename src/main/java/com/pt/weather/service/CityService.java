package com.pt.weather.service;

import com.pt.weather.model.dto.CityResponse;

import java.util.List;

public interface CityService {
  CityResponse createCity(String cityName);
  List<CityResponse> getAllCities();
  CityResponse getCityById(Long id);
}
