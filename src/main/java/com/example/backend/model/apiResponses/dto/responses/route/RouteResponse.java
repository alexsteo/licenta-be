package com.example.backend.model.apiResponses.dto.responses.route;

import com.example.backend.model.apiResponses.dto.shared.Coordinate;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RouteResponse {

    private List<Coordinate> route1;
    private List<Coordinate> route2;
    private List<Coordinate> route3;
}
