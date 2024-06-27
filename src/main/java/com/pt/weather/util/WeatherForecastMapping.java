package com.pt.weather.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WeatherForecastMapping {

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class WeatherForecastJsonProperties {
    public static final String DT         = "dt";
    public static final String MAIN       = "main";
    public static final String WEATHER    = "weather";
    public static final String CLOUDS     = "clouds";
    public static final String WIND       = "wind";
    public static final String VISIBILITY = "visibility";
    public static final String POP        = "pop";
    public static final String RAIN       = "rain";
    public static final String SYS        = "sys";
    public static final String DT_TXT     = "dt_txt";
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class CityForecastJsonProperties {
    public static final String ID         = "id";
    public static final String NAME       = "name";
    public static final String COORD      = "coord";
    public static final String COUNTRY    = "country";
    public static final String POPULATION = "population";
    public static final String TIMEZONE   = "timezone";
    public static final String SUNRISE    = "sunrise";
    public static final String SUNSET     = "sunset";
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class CordJsonProperties {
    public static final String LAT = "lat";
    public static final String LON = "lon";
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class MainJsonProperties {
    public static final String TEMP       = "temp";
    public static final String FEELS_LIKE = "feels_like";
    public static final String TEMP_MIN   = "temp_min";
    public static final String TEMP_MAX   = "temp_max";
    public static final String PRESSURE   = "pressure";
    public static final String SEA_LEVEL  = "sea_level";
    public static final String GRND_LEVEL = "grnd_level";
    public static final String HUMIDITY   = "humidity";
    public static final String TEMP_KF    = "temp_kf";
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class CloudsJsonProperties {
    public static final String ALL = "all";
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class WeatherJsonProperties {
    public static final String ID          = "id";
    public static final String MAIN        = "main";
    public static final String DESCRIPTION = "description";
    public static final String ICON        = "icon";
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class WindJsonProperties {
    public static final String SPEED = "speed";
    public static final String DEG   = "deg";
    public static final String GUST  = "gust";
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class RainJsonProperties {
    public static final String THREE_HOURS = "3h";
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class SysJsonProperties {
    public static final String POD = "pod";
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class WeatherResponse {
    public static final String COD     = "cod";
    public static final String MESSAGE = "message";
    public static final String CNT     = "cnt";
    public static final String LIST    = "list";
    public static final String CITY    = "city";
  }
}
