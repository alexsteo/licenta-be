package com.example.backend.model.dto.responses.my;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LocationWithWeatherHourly {
    private List<LocationWithWeather> weatherInLocations;
    private Integer plusHours;

    public LocationWithWeatherHourly(Integer plusHours) {
        this.weatherInLocations = new ArrayList<>();
        this.plusHours = plusHours;
    }

    public void addWeatherLocation(LocationWithWeather locationWithWeather) {
        weatherInLocations.add(locationWithWeather);
    }
}
