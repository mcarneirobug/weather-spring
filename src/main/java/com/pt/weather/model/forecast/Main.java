package com.pt.weather.model.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static com.pt.weather.util.WeatherForecastMapping.MainJsonProperties.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Main {

  @JsonProperty(TEMP)
  private double temp;

  @JsonProperty(FEELS_LIKE)
  private double feelsLike;

  @JsonProperty(TEMP_MIN)
  private double tempMin;

  @JsonProperty(TEMP_MAX)
  private double tempMax;

  @JsonProperty(PRESSURE)
  private int pressure;

  @JsonProperty(SEA_LEVEL)
  private int seaLevel;

  @JsonProperty(GRND_LEVEL)
  private int grndLevel;

  @JsonProperty(HUMIDITY)
  private int humidity;

  @JsonProperty(TEMP_KF)
  private double tempKf;
}
