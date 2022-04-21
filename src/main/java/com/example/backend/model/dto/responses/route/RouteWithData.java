package com.example.backend.model.dto.responses.route;

import com.example.backend.model.shared.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class RouteWithData {

    private Double length;
    private Double score;
    private List<Coordinate> route;

    public RouteWithData (List<Coordinate> route, Double length) {
        this.route = route;
        this.length = length;
    }
}
