package com.pt.weather.controller;

import com.pt.weather.exception.InvalidCityException;

import com.pt.weather.model.dto.CityResponse;

import com.pt.weather.service.CityService;

import lombok.RequiredArgsConstructor;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static com.pt.weather.util.Constants.Hateoas.DAY_3;
import static com.pt.weather.util.Constants.Hateoas.DAY_5;
import static com.pt.weather.util.Constants.Hateoas.FORECAST_3_DAYS;
import static com.pt.weather.util.Constants.Hateoas.FORECAST_5_DAYS;
import static com.pt.weather.util.Constants.Hateoas.SELF_RELATION;

import static com.pt.weather.util.Constants.Url.Api.RESOURCE_CITIES;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping(RESOURCE_CITIES)
public class CityController {

  private final CityService cityService;

  @PostMapping
  public ResponseEntity<EntityModel<CityResponse>> createCity(@RequestBody String cityName) {
    try {
      final var cityResponse = cityService.createCity(cityName);
      EntityModel<CityResponse> resource = EntityModel.of(cityResponse,
          linkTo(methodOn(CityController.class).getCity(cityResponse.id())).withSelfRel(),
          linkTo(methodOn(ForecastController.class).getForecast(cityResponse.name(), DAY_3)).withRel(
              FORECAST_3_DAYS),
          linkTo(methodOn(ForecastController.class).getForecast(cityResponse.name(), DAY_5)).withRel(
              FORECAST_5_DAYS)
          );

      return ResponseEntity.created(URI.create(resource.getRequiredLink(SELF_RELATION).getHref())).body(resource);
    } catch (InvalidCityException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EntityModel.of(CityResponse.withDefaults(), linkTo(CityController.class).withSelfRel()));
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<CityResponse>> getCity(@PathVariable Long id) {
    final CityResponse cityResponse = cityService.getCityById(id);
    final EntityModel<CityResponse> resource = EntityModel.of(cityResponse);

    resource.add(linkTo(methodOn(CityController.class).getCity(id)).withSelfRel());

    return ResponseEntity.ok(resource);
  }

  @GetMapping
  public ResponseEntity<CollectionModel<EntityModel<CityResponse>>> getAllCities() {
    List<EntityModel<CityResponse>> cities = cityService.getAllCities().stream()
        .map(city -> EntityModel.of(city,
            linkTo(methodOn(CityController.class).getCity(city.id())).withSelfRel(),
            linkTo(methodOn(ForecastController.class).getForecast(city.name(), DAY_3)).withRel(FORECAST_3_DAYS),
            linkTo(methodOn(ForecastController.class).getForecast(city.name(), DAY_5)).withRel(FORECAST_5_DAYS)
            ))
        .toList();

    return ResponseEntity.ok(CollectionModel.of(cities, linkTo(methodOn(CityController.class).getAllCities()).withSelfRel()));
  }
}
