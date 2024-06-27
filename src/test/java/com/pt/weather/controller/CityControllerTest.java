package com.pt.weather.controller;

import com.pt.weather.exception.InvalidCityException;
import com.pt.weather.exception.advice.GlobalControllerExceptionHandler;

import com.pt.weather.model.dto.CityResponse;

import com.pt.weather.service.CityService;

import lombok.SneakyThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CityControllerTest {

  private static final String LONDON               = "London";
  private static final String PARIS                = "Paris";
  private static final long   ID_LONDON            = 1L;
  private static final long   ID_PARIS             = 2L;
  private static final String PATH_CITIES_RESOURCE = "/api/cities";
  private static final String PATH_CITIES_ID       = "/api/cities/{id}";
  private static final String CITY_IS_NOT_VALID    = "City is not valid";

  @Mock
  private CityService cityService;

  @InjectMocks
  private CityController cityController;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(cityController)
        .setControllerAdvice(new GlobalControllerExceptionHandler()).build();
  }

  @Test
  @DisplayName("POST api/cities - Success")
  void shouldCreateCitySuccess() throws Exception {
    // given
    final CityResponse cityResponse = getCitiesResponse().get(0);
    final String cityName = cityResponse.name();

    given(cityService.createCity(cityName)).willReturn(cityResponse);

    // when
    mockMvc.perform(post(PATH_CITIES_RESOURCE)
            .contentType(MediaType.APPLICATION_JSON)
            .content(cityName))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value(cityName));

    // then
    then(cityService).should().createCity(cityName);
  }

  @Test
  @SneakyThrows
  @DisplayName("POST api/cities - Invalid City")
  void shouldCreateCityFail() {
    // given
    final String cityName = "InvalidCity";

    given(cityService.createCity(cityName)).willThrow(new InvalidCityException(CITY_IS_NOT_VALID));

    // when
    mockMvc.perform(
            post(PATH_CITIES_RESOURCE).contentType(MediaType.APPLICATION_JSON).content(cityName))
        .andExpect(status().isBadRequest()).andDo(print());

    // then
    then(cityService).should().createCity(cityName);
  }

  @Test
  @SneakyThrows
  @DisplayName("GET api/cities/{id} - Success")
  void shouldGetCityByIdSuccess() {
    // given
    final CityResponse cityResponse = getCitiesResponse().get(0);
    final Long idCity = cityResponse.id();

    given(cityService.getCityById(idCity)).willReturn(cityResponse);

    // when
    mockMvc.perform(get(PATH_CITIES_ID, idCity)).andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(idCity))
        .andExpect(jsonPath("$.name").value(cityResponse.name())).andDo(print());

    // then
    then(cityService).should().getCityById(idCity);
  }

  @Test
  @SneakyThrows
  @DisplayName("GET api/cities - List Cities")
  void shouldGetAllCities() {
    // given
    given(cityService.getAllCities()).willReturn(getCitiesResponse());

    // when
    mockMvc.perform(get(PATH_CITIES_RESOURCE)).andExpect(status().isOk())
        .andExpect(jsonPath("$['content'][0]['id']").value(ID_LONDON))
        .andExpect(jsonPath("$['content'][0]['name']").value(LONDON))
        .andExpect(jsonPath("$['content'][1]['id']").value(ID_PARIS))
        .andExpect(jsonPath("$['content'][1]['name']").value(PARIS));

    // then
    then(cityService).should().getAllCities();
  }

  private List<CityResponse> getCitiesResponse() {
    final CityResponse cityResponse1 = new CityResponse(ID_LONDON, LONDON);
    final CityResponse cityResponse2 = new CityResponse(ID_PARIS, PARIS);

    return List.of(cityResponse1, cityResponse2);
  }
}
