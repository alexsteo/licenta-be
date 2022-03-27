package com.example.backend.model.dto.responses.my;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FavouritesWeatherResponse {
    List<FavouriteWeatherEntry> entries;
}
