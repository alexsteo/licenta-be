package com.example.backend.model.dto.responses.my;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FavouriteWeatherEntry {
    private Double temperature;
    private String name;
    private Double lat;
    private Double lng;
    private String city;
}
