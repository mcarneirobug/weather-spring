package com.pt.weather.model.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static com.pt.weather.util.WeatherForecastMapping.CloudsJsonProperties.ALL;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Clouds {

  @JsonProperty(ALL)
  private int all;
}
