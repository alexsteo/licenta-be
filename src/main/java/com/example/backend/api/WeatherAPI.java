package com.example.backend.api;

import com.example.backend.model.apiResponses.apis.weather.WeatherAPIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherAPI {
    public static WeatherAPIResponse getWeather() throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=46.769661&lon=23.590597&appid=48c84905bdb6497273493f657bf32e20");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("accept", "application/json");
        InputStream responseStream = con.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        WeatherAPIResponse response = mapper.readValue(responseStream, WeatherAPIResponse.class);
        return response;
    }
}
