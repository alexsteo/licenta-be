package com.example.backend.model.dto.responses.my;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LocationWithWeatherDaily {
    private List<LocationWithWeather> dailyWeatherData;
    private Double lat;
    private Double lng;
    private String cityName;

    public void setData(List<LocationWithWeather> data) {
        this.setDailyWeatherData(data);
        this.setCityName(data.get(0).getLocation());
        this.setLat(data.get(0).getLat());
        this.setLng(data.get(0).getLng());
    }
}
