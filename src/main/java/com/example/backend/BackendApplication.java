package com.example.backend;

import com.example.backend.api.WeatherAPI;
import com.example.backend.model.apis.weather.forecast.WeatherForecastAPIResponse;
import com.example.backend.model.dto.requests.route.RouteRequest;
import com.example.backend.model.dto.requests.weather.WeatherLocationRequest;
import com.example.backend.service.CityCsvService;
import com.example.backend.service.RouteService;
import com.opencsv.exceptions.CsvException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.*;

@SpringBootApplication
public class BackendApplication {

    public static int weatherCalls = 0;

    public static void main(String[] args) throws IOException, CsvException {
        initialize();
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private static void initialize() {
        CityCsvService.initializeCsvEntries();
    }
}
