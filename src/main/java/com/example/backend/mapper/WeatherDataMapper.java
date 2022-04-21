package com.example.backend.mapper;

import com.example.backend.model.db.WeatherData;
import com.example.backend.model.dto.responses.my.LocationWithWeather;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

public class WeatherDataMapper {
    public static LocationWithWeather convertWeatherData(WeatherData data) {
        return LocationWithWeather.builder()
                .plusHours(calculatePlusHours(data.getTimestamp()))
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

    private static Integer calculatePlusHours(Timestamp timestamp) {

        LocalDateTime weatherTime = timestamp.toLocalDateTime();
        LocalDateTime currentTime = LocalDateTime.now();
        return (int) (Duration.between(currentTime, weatherTime).getSeconds() / 3600);
    }
}
