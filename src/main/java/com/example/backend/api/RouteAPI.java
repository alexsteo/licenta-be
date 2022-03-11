package com.example.backend.api;

import com.example.backend.model.apiResponses.apis.route.RouteAPIResponse;
import com.example.backend.model.apiResponses.dto.requests.route.RouteRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class RouteAPI {
    public static RouteAPIResponse getDirections(RouteRequest request) throws IOException {
        String urlString = String
                .format("http://www.mapquestapi.com/directions/v2/alternateroutes?key=3Fu1qGMCbaacUIQxy03WCVZAT7Qhvr1f&from=%s&to=%s&maxRoutes=3", request.getFrom(), request.getTo());
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("accept", "application/json");
        InputStream responseStream = con.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        RouteAPIResponse response = mapper.readValue(responseStream, RouteAPIResponse.class);
        return response;
    }
}
