package com.pt.weather.service.impl;

import com.pt.weather.exception.ApiException;
import com.pt.weather.service.CityValidationService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.pt.weather.util.Constants.ExceptionMessage.FAILED_TO_VERIFY_CITY_DUE_TO_AN_UNEXPECTED_ERROR;

import static com.pt.weather.util.Constants.LogMessage.CITY_NOT_FOUND;

import static com.pt.weather.util.Constants.Url.QueryParam.API_KEY;
import static com.pt.weather.util.Constants.Url.QueryParam.CITY_DETAILS;

import static com.pt.weather.util.Constants.Url.Resource.WEATHER;

@Service
@RequiredArgsConstructor
public class CityValidationServiceImpl implements CityValidationService {

  private static final Logger logger = LoggerFactory.getLogger(CityValidationServiceImpl.class);

  @Value("${weather.api.key}")
  private String apiKey;

  @Value("${weather.api.url}")
  private String apiUrl;

  private final RestTemplate restTemplate;

  @Override
  public boolean isValidCity(final String cityName) {

    String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
        .path(WEATHER)
        .queryParam(CITY_DETAILS, cityName)
        .queryParam(API_KEY, apiKey)
        .toUriString();

    try {
      restTemplate.getForObject(url, String.class);
      return true;
    } catch (HttpClientErrorException.NotFound e) {
      logger.error(CITY_NOT_FOUND, cityName, e);
      return false;
    } catch (Exception e) {
      throw new ApiException(FAILED_TO_VERIFY_CITY_DUE_TO_AN_UNEXPECTED_ERROR, e);
    }
  }
}
