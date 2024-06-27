package com.pt.weather.model.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static com.pt.weather.util.WeatherForecastMapping.WeatherJsonProperties.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weather {

  @JsonProperty(ID)
  private int id;

  @JsonProperty(MAIN)
  private String main;

  @JsonProperty(DESCRIPTION)
  private String description;

  @JsonProperty(ICON)
  private String icon;
}
