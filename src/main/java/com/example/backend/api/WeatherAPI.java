package com.example.backend.api;

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
        String urlString = "https://api.openweathermap.org/data/2.5/onecall?lat=%f&lon=%f&appid=48c84905bdb6497273493f657bf32e20";
        return api.get(urlString, WeatherForecastAPIResponse.class, 46.770439, 23.591423);
    }

    public WeatherCurrentAPIResponse getWeatherCurrentForLocation(WeatherLocationRequest request) {
        String urlString = "https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=48c84905bdb6497273493f657bf32e20";
        return api.get(urlString, WeatherCurrentAPIResponse.class, 46.770439, 23.591423);
    }
}
