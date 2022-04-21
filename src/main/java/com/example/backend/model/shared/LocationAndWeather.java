package com.example.backend.model.shared;

import com.example.backend.model.csv.CityCsvEntry;
import com.example.backend.model.db.WeatherData;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LocationAndWeather {
    private List<WeatherData> weather;
    private CityCsvEntry location;
}
