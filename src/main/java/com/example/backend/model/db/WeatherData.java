package com.example.backend.model.db;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "weather_data", schema= "licenta")
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "clouds")
    private Integer clouds;

    @Column(name = "wind_speed")
    private Double windSpeed;

    @Column(name = "rain")
    private Double rain;

    @Column(name = "snow")
    private Double snow;

    @Column(name = "weather_description")
    private String weatherDescription;

    @Column(name = "location")
    private String location;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lng")
    private Double lng;
}
