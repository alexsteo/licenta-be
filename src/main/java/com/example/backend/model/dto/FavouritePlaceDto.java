package com.example.backend.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FavouritePlaceDto {
    private String username;
    private Double lat;
    private Double lng;
    private String city;
}
