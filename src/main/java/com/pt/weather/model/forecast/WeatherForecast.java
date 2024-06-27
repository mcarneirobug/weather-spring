package com.pt.weather.model.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.pt.weather.util.WeatherForecastMapping.WeatherForecastJsonProperties.CLOUDS;
import static com.pt.weather.util.WeatherForecastMapping.WeatherForecastJsonProperties.DT;
import static com.pt.weather.util.WeatherForecastMapping.WeatherForecastJsonProperties.DT_TXT;
import static com.pt.weather.util.WeatherForecastMapping.WeatherForecastJsonProperties.MAIN;
import static com.pt.weather.util.WeatherForecastMapping.WeatherForecastJsonProperties.POP;
import static com.pt.weather.util.WeatherForecastMapping.WeatherForecastJsonProperties.RAIN;
import static com.pt.weather.util.WeatherForecastMapping.WeatherForecastJsonProperties.SYS;
import static com.pt.weather.util.WeatherForecastMapping.WeatherForecastJsonProperties.VISIBILITY;
import static com.pt.weather.util.WeatherForecastMapping.WeatherForecastJsonProperties.WEATHER;
import static com.pt.weather.util.WeatherForecastMapping.WeatherForecastJsonProperties.WIND;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherForecast {

  @JsonProperty(DT)
  private long dateTime;

  @JsonProperty(MAIN)
  private Main main;

  @JsonProperty(WEATHER)
  private List<Weather> weather;

  @JsonProperty(CLOUDS)
  private Clouds clouds;

  @JsonProperty(WIND)
  private Wind wind;

  @JsonProperty(VISIBILITY)
  private int visibility;

  @JsonProperty(POP)
  private double pop;

  @JsonProperty(RAIN)
  private Rain rain;

  @JsonProperty(SYS)
  private Sys sys;

  @JsonProperty(DT_TXT)
  private String dateTimeText;
}
