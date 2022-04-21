package com.example.backend.api;

import com.example.backend.model.apis.route.RouteAPIResponse;
import com.example.backend.model.dto.requests.route.RouteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteAPI{

    @Autowired
    private API api;

    public RouteAPIResponse getDirections(RouteRequest request) {
        String urlString = "http://www.mapquestapi.com/directions/v2/alternateroutes?key=3Fu1qGMCbaacUIQxy03WCVZAT7Qhvr1f&from=%s&to=%s&maxRoutes=5";
        return api.get(urlString, RouteAPIResponse.class, request.getFrom(), request.getTo());
    }

    public RouteAPIResponse getLocalDirections(RouteRequest request) {
        String urlString = "http://localhost:3000/";
        String urlString2 = "http://www.mapquestapi.com/directions/v2/alternateroutes?key=3Fu1qGMCbaacUIQxy03WCVZAT7Qhvr1f&from=%s&to=%s&maxRoutes=5";
        return api.get(urlString, RouteAPIResponse.class, request.getFrom(), request.getTo());
    }
}
