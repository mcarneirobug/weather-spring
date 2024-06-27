package com.pt.weather.service;

import com.pt.weather.model.dto.WeatherResponse;

public interface ForecastService {
  WeatherResponse getWeatherForecast(String cityName, int days);
}
