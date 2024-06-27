package com.pt.weather.model.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static com.pt.weather.util.WeatherForecastMapping.SysJsonProperties.POD;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sys {

  @JsonProperty(POD)
  private String pod;
}
