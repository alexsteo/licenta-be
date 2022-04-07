package com.example.backend.service;

import com.example.backend.api.WeatherAPI;
import com.example.backend.model.apis.weather.current.WeatherCurrentAPIResponse;
import com.example.backend.model.apis.weather.forecast.WeatherForecastAPIResponse;
import com.example.backend.model.dto.requests.weather.WeatherLocationRequest;
import com.example.backend.model.dto.responses.weather.WeatherLocationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Autowired
    private WeatherAPI weatherAPI;

    public WeatherLocationResponse getCurrentWeather(WeatherLocationRequest request) {
        WeatherCurrentAPIResponse apiResponse = weatherAPI.getWeatherCurrentForLocation(request.getLat(), request.getLng());
        WeatherLocationResponse response = new WeatherLocationResponse();
        response.setTemperature(apiResponse.getMain().getTemp());
        response.setName(apiResponse.getName());
        response.setLat(apiResponse.getCoord().getLat());
        response.setLng(apiResponse.getCoord().getLon());
        return response;
    }

    public WeatherForecastAPIResponse getWeatherForecast(Double lat, Double lng) {
        return weatherAPI.getWeatherForecastForLocation(new WeatherLocationRequest(lat, lng));
    }
}
