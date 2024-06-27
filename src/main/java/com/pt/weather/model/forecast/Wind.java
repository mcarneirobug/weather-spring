package com.pt.weather.model.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static com.pt.weather.util.WeatherForecastMapping.WindJsonProperties.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wind {

  @JsonProperty(SPEED)
  private double speed;

  @JsonProperty(DEG)
  private int deg;

  @JsonProperty(GUST)
  private double gust;
}
