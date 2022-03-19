package com.example.backend.service;

import com.example.backend.api.RouteAPI;
import com.example.backend.model.apis.route.AlternateRoute;
import com.example.backend.model.apis.route.RouteAPIResponse;
import com.example.backend.model.dto.requests.route.RouteRequest;
import com.example.backend.model.dto.responses.route.RouteResponse;
import com.example.backend.model.shared.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteAPI routeAPI;

    public RouteResponse getRoute(RouteRequest request) {
        RouteAPIResponse response;
        response = routeAPI.getDirections(request);
        RouteResponse routeResponse = new RouteResponse(createResponseFromShapePoints(response.getRoute().getShape().getShapePoints()));
        if (response.getRoute().getAlternateRoutes() != null) {
            for (AlternateRoute alternateRoute : response.getRoute().getAlternateRoutes()) {
                routeResponse.addRoute(createResponseFromShapePoints(alternateRoute.getRoute().getShape().getShapePoints()));
            }
        }
        return routeResponse;
    }

    private List<Coordinate> createResponseFromShapePoints(List<Double> shapePoints) {
        List<Coordinate> returnable = new ArrayList<>();
        for (int i = 0; i < shapePoints.size(); i += 2) {
            returnable.add(new Coordinate(shapePoints.get(i), shapePoints.get(i + 1)));
        }
        return returnable;
    }
}
