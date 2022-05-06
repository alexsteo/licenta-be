package com.example.backend.api;

import com.example.backend.BackendApplication;
import com.example.backend.model.apis.weather.current.WeatherCurrentAPIResponse;
import com.example.backend.model.apis.weather.forecast.WeatherForecastAPIResponse;
import com.example.backend.model.dto.requests.weather.WeatherLocationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherAPI {

    @Autowired
    private API api;

    public WeatherForecastAPIResponse getWeatherForecastForLocation(WeatherLocationRequest request) {
        String urlString = "https://api.openweathermap.org/data/2.5/onecall?lat=%f&lon=%f&appid=48c84905bdb6497273493f657bf32e20&units=metric";
        BackendApplication.weatherCalls++;
        return api.get(urlString, WeatherForecastAPIResponse.class, request.getLat(), request.getLng());
    }

    public WeatherCurrentAPIResponse getWeatherCurrentForLocation(Double lat, Double lng) {
        String urlString = "https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=48c84905bdb6497273493f657bf32e20&units=metric";
        return api.get(urlString, WeatherCurrentAPIResponse.class, lat, lng);
    }
}
