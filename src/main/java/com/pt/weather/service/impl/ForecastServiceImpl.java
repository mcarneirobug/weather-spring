package com.pt.weather.service.impl;

import com.pt.weather.model.dto.WeatherResponse;
import com.pt.weather.service.ForecastService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.pt.weather.util.Constants.Url.QueryParam.API_KEY;
import static com.pt.weather.util.Constants.Url.QueryParam.CITY_DETAILS;
import static com.pt.weather.util.Constants.Url.QueryParam.CNT_TIMESTAMP;
import static com.pt.weather.util.Constants.Url.Resource.FORECAST;

@Service
@RequiredArgsConstructor
public class ForecastServiceImpl implements ForecastService {

  @Value("${weather.api.key}")
  private String apiKey;

  @Value("${weather.api.url}")
  private String apiUrl;

  private final RestTemplate restTemplate;

  @Override
  public WeatherResponse getWeatherForecast(final String cityName, final int days) {
    String url = buildForecastUrl(cityName, days);

    return restTemplate.getForObject(url, WeatherResponse.class);
  }

  private String buildForecastUrl(String cityName, int days) {
    return UriComponentsBuilder.fromHttpUrl(apiUrl)
        .path(FORECAST)
        .queryParam(CITY_DETAILS, cityName)
        .queryParam(CNT_TIMESTAMP, days * 8)  // Each day has 8 three-hour segments
        .queryParam(API_KEY, apiKey)
        .toUriString();
  }
}
