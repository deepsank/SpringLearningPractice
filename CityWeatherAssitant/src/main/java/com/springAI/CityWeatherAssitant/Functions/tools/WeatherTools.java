package com.springAI.CityWeatherAssitant.Functions.tools;

import com.springAI.CityWeatherAssitant.Functions.service.WeatherService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class WeatherTools {

    private final WeatherService weatherService;

    public WeatherTools(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Tool(description = "Get the current weather conditions for the given city")
    public WeatherService.Response currentWeather(String city) {
        return weatherService.getWeather(city);
    }
}