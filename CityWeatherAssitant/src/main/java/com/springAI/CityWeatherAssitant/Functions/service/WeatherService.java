package com.springAI.CityWeatherAssitant.Functions.service;

import com.springAI.CityWeatherAssitant.Functions.configs.WeatherConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

@Service
public class WeatherService {

    private final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    private final WeatherConfigProperties weatherProps;
    private final RestClient restClient;

    public WeatherService(WeatherConfigProperties weatherProps){
        this.weatherProps = weatherProps;
        this.restClient = RestClient.create(weatherProps.apiUrl());
    }

    public Response getWeather(String city) {
        logger.info("Weather Request: {}", city);

        Response response = restClient.get()
                .uri("/current.json?key={key}&q={q}",
                        weatherProps.apiKey(), city)
                .retrieve()
                .body(Response.class);

        logger.info("Weather API Response: {}", response);
        return response;
    }

    public record Request(String city){}
    public record Response(Location location, Current current ){}
    public record Location(String name, String region, String country, Long lat, Long lon){}
    public record Current(String temp_f, Condition condition, String wind_mph, String humidity){}
    public record Condition(String text){}
}

