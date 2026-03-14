package com.springAI.CityWeatherAssitant.Functions;

import com.springAI.CityWeatherAssitant.Functions.configs.WeatherConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(WeatherConfigProperties.class)
public class CityWeatherAssitantApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityWeatherAssitantApplication.class, args);
	}

}
