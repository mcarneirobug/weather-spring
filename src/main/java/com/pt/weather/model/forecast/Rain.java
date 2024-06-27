package com.pt.weather.model.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static com.pt.weather.util.WeatherForecastMapping.RainJsonProperties.THREE_HOURS;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rain {

  @JsonProperty(THREE_HOURS)
  private double threeHour;
}
