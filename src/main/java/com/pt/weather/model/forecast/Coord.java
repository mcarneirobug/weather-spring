package com.pt.weather.model.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static com.pt.weather.util.WeatherForecastMapping.CordJsonProperties.LAT;
import static com.pt.weather.util.WeatherForecastMapping.CordJsonProperties.LON;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coord {

  @JsonProperty(LAT)
  private double lat;

  @JsonProperty(LON)
  private double lon;
}
