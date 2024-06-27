package com.pt.weather.model.dto;

import com.pt.weather.model.City;

public record CityResponse(Long id, String name) {
  public static CityResponse toDto(City city) {
    if (city == null) {
      return null;
    }
    return new CityResponse(city.getId(), city.getName());
  }

  public static CityResponse withDefaults() {
    return new CityResponse(null, "Unknown");
  }
}
