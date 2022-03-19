package com.example.backend.controller;

import com.example.backend.model.dto.requests.route.RouteRequest;
import com.example.backend.model.dto.requests.weather.WeatherLocationRequest;
import com.example.backend.model.dto.responses.route.RouteResponse;
import com.example.backend.model.dto.responses.weather.WeatherLocationResponse;
import com.example.backend.service.RouteService;
import com.example.backend.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/weather")
@Slf4j
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    public WeatherController() {
    }

    @GetMapping("/{searchTerm}")
    public WeatherLocationResponse getRouteTo(@PathVariable("searchTerm") String searchTerm) {
        WeatherLocationRequest request = new WeatherLocationRequest(searchTerm);
        return weatherService.getCurrentWeather(request);
    }
}
