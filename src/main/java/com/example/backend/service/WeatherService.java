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
        response.setClouds(apiResponse.getClouds().getAll());
        if(apiResponse.getRain() != null) {
            response.setRain(apiResponse.getRain().get_1h());
        } else {
            response.setRain(0.0);
        }
        if(apiResponse.getSnow() != null) {
            response.setSnow(apiResponse.getSnow().get_1h());
        } else {
            response.setSnow(0.0);
        }
        return response;
    }

    public WeatherForecastAPIResponse getWeatherForecast(Double lat, Double lng) {
        return weatherAPI.getWeatherForecastForLocation(new WeatherLocationRequest(lat, lng));
    }
}
