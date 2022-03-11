package com.example.backend.service;

import com.example.backend.api.RouteAPI;
import com.example.backend.model.apiResponses.apis.route.RouteAPIResponse;
import com.example.backend.model.apiResponses.dto.requests.route.RouteRequest;
import com.example.backend.model.apiResponses.dto.responses.route.RouteResponse;
import com.example.backend.model.apiResponses.dto.shared.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteAPI routeAPI;

    public RouteResponse getRoute(RouteRequest request) {
        RouteAPIResponse response;
        try {
            response = routeAPI.getDirections(request);
        } catch (IOException e) {
            return new RouteResponse();
        }
        RouteResponse routeResponse = new RouteResponse();
        routeResponse.setRoute1(createResponseFromShapePoints(response.getRoute().getShape().getShapePoints()));
        if(response.getRoute().getAlternateRoutes() != null && response.getRoute().getAlternateRoutes().size() > 0 && response.getRoute().getAlternateRoutes().get(0) != null) {
            routeResponse.setRoute2(createResponseFromShapePoints(response.getRoute().getAlternateRoutes().get(0).getRoute().getShape().getShapePoints()));
        }
        if(response.getRoute().getAlternateRoutes() != null && response.getRoute().getAlternateRoutes().size() > 1 && response.getRoute().getAlternateRoutes().get(1) != null) {
            routeResponse.setRoute3(createResponseFromShapePoints(response.getRoute().getAlternateRoutes().get(1).getRoute().getShape().getShapePoints()));
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
