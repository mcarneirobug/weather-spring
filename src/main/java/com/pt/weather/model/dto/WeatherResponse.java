package com.pt.weather.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pt.weather.model.forecast.CityForecast;
import com.pt.weather.model.forecast.WeatherForecast;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.pt.weather.util.WeatherForecastMapping.WeatherResponse.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {

  @JsonProperty(COD)
  private String cod;

  @JsonProperty(MESSAGE)
  private int message;

  @JsonProperty(CNT)
  private int cnt;

  @JsonProperty(LIST)
  private List<WeatherForecast> list;

  @JsonProperty(CITY)
  private CityForecast city;
}
