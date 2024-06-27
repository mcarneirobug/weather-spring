package com.pt.weather.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class Database {

    public static final String FIELD_NAME = "NAME";
    public static final String TABLE_CITY = "city";
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class Url {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Resource {
      public static final String WEATHER  = "weather";
      public static final String FORECAST = "forecast";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Api {
      public static final String RESOURCE_FORECAST = "/api/forecast";
      public static final String RESOURCE_CITIES   = "/api/cities";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class QueryParam {
      public static final String CITY_DETAILS  = "q";
      public static final String API_KEY       = "appid";
      public static final String CNT_TIMESTAMP = "cnt";
    }
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class Hateoas {
    public static final String FORECAST_3_DAYS = "forecast-3-days";
    public static final String FORECAST_5_DAYS = "forecast-5-days";
    public static final String SELF_RELATION   = "self";
    public static final int    DAY_3           = 3;
    public static final int    DAY_5           = 5;
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class LogMessage {
    public static final String CITY_NOT_FOUND                                  = "City not found: <{}>.";
    public static final String ERROR_OCCURRED_WHILE_FETCHING_FORECAST_FOR_CITY = "Error occurred while fetching forecast for city <{}>: <{}>.";
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class ExceptionMessage {
    public static final String FAILED_TO_VERIFY_CITY_DUE_TO_AN_UNEXPECTED_ERROR = "Failed to verify city due to an unexpected error";
    public static final String CITY_IS_NOT_VALID_TO_CREATE                      = "City is not valid to create.";
    public static final String COULD_NOT_FIND_CITY_WITH_ID                      = "Could not find city with id: %d";
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class ProblemDetail {
    public static final String ERROR_OCCURRED_WHILE_PROCESSING_YOUR_REQUEST = "An unexpected error occurred while processing your request. Please try again later.";
    public static final String INTERNAL_SERVER_ERROR_TITLE                  = "Internal Server Error";
    public static final String INVALID_REQUEST_PARAMETER_TITLE              = "Invalid Request Parameter";
    public static final String FORECAST_INSTANCE                            = "/api/forecast/";
    public static final String THE_DAYS_PARAMETER_MUST_BE_EITHER_3_OR_5     = "The 'days' parameter must be either 3 or 5.";
  }
}
