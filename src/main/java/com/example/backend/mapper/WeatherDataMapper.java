package com.example.backend.mapper;

import com.example.backend.model.db.WeatherData;
import com.example.backend.model.dto.responses.my.LocationWithWeather;

public class WeatherDataMapper {
    public static LocationWithWeather convertWeatherData(WeatherData data) {
        return LocationWithWeather.builder()
                .timestamp(data.getTimestamp())
                .temperature(data.getTemperature())
                .clouds(data.getClouds())
                .windSpeed(data.getWindSpeed())
                .rain(data.getRain())
                .snow(data.getSnow())
                .weatherDescription(data.getWeatherDescription())
                .location(data.getLocation())
                .lat(data.getLat())
                .lng(data.getLng())
                .build();
    }
}
