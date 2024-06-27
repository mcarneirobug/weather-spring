package com.pt.weather.model.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static com.pt.weather.util.WeatherForecastMapping.CityForecastJsonProperties.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityForecast {

  @JsonProperty(ID)
  private long id;

  @JsonProperty(NAME)
  private String name;

  @JsonProperty(COORD)
  private Coord coord;

  @JsonProperty(COUNTRY)
  private String country;

  @JsonProperty(POPULATION)
  private int population;

  @JsonProperty(TIMEZONE)
  private int timezone;

  @JsonProperty(SUNRISE)
  private long sunrise;

  @JsonProperty(SUNSET)
  private long sunset;
}
