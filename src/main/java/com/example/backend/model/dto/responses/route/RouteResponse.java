package com.example.backend.model.dto.responses.route;

import com.example.backend.model.shared.Coordinate;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RouteResponse {

    public RouteResponse(List<Coordinate> coordinateList) {
        this.routes = new ArrayList<>();
        this.routes.add(coordinateList);
    }

    private List<List<Coordinate>> routes;

    public void addRoute(List<Coordinate> newRoute) {
        routes.add(newRoute);
    }
}
