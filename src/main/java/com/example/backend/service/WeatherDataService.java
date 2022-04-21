package com.example.backend.service;

import com.example.backend.model.apis.weather.forecast.Hourly;
import com.example.backend.model.apis.weather.forecast.WeatherForecastAPIResponse;
import com.example.backend.model.csv.CityCsvEntry;
import com.example.backend.model.db.WeatherData;
import com.example.backend.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WeatherDataService {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Autowired
    private WeatherService weatherService;

    public List<WeatherData> insertData(List<WeatherData> data) {
        return weatherDataRepository.saveAllAndFlush(data);
    }

    public List<WeatherData> getForecastAndSave(CityCsvEntry location) {
        WeatherForecastAPIResponse apiRespone = weatherService.getWeatherForecast(location.getLat(), location.getLng());
        List<WeatherData> weatherData = apiRespone.hourly
                .stream()
                .map(hourly -> mapHourlyDataToWeatherData(hourly, location))
                .filter(data -> data.getTimestamp().toLocalDateTime().getDayOfYear() < LocalDateTime.now().getDayOfYear() + 2)
                .filter(data -> data.getTimestamp().toLocalDateTime().getHour() != LocalDateTime.now().getHour())
                .collect(Collectors.toList());
        weatherDataRepository.saveAllAndFlush(weatherData);
        return weatherData.stream().limit(24).collect(Collectors.toList());
    }

    public List<WeatherData> getWeatherForLocation(CityCsvEntry location) {
        return weatherDataRepository.getByLocation(location.getCity());
    }

    public List<WeatherData> getWeatherOrDeleteIfOld(CityCsvEntry location) {
        List<WeatherData> data = weatherDataRepository.getByLocationForFuture(location.getCity(), Timestamp.from(Instant.now()));
        if (data.size() < 24) {
            weatherDataRepository.deleteByLocation(location.getCity());
            return new ArrayList<>();
        }
        return data;
    }

    public List<WeatherData> getWeatherOrDeleteAndGetIfOld(CityCsvEntry location) {
        List<WeatherData> data = weatherDataRepository.getByLocationForFuture(location.getCity(), Timestamp.from(Instant.now()));
        if (data.size() < 24) {
            weatherDataRepository.deleteByLocation(location.getCity());
            return getForecastAndSave(location);
        }
        return data;
    }

    public void deleteOldData() {
        weatherDataRepository.deleteByDays(Timestamp.from(Instant.now()));
    }

    private WeatherData mapHourlyDataToWeatherData(Hourly hourlyData, CityCsvEntry location) {
        return WeatherData
                .builder()
                .timestamp(Timestamp.from(Instant.ofEpochSecond(hourlyData.getDt())))
                .temperature(hourlyData.getTemp())
                .clouds(hourlyData.getClouds())
                .windSpeed(hourlyData.getWind_speed())
                .rain(hourlyData.getRain() != null && hourlyData.getRain().get_1h() != null ? hourlyData.getRain().get_1h() : 0)
                .snow(hourlyData.getSnow() != null && hourlyData.getSnow().get_1h() != null ? hourlyData.getSnow().get_1h() : 0)
                .weatherDescription(hourlyData.getWeather().get(0).getDescription())
                .location(location.getCity())
                .lat(location.getLat())
                .lng(location.getLng())
                .build();
    }
}
