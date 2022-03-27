package com.example.backend.service;

import com.example.backend.model.db.FavouritePlace;
import com.example.backend.model.db.UserReport;
import com.example.backend.model.dto.requests.weather.WeatherLocationRequest;
import com.example.backend.model.dto.responses.my.FavouriteWeatherEntry;
import com.example.backend.model.dto.responses.my.FavouritesWeatherResponse;
import com.example.backend.model.dto.responses.weather.WeatherLocationResponse;
import com.example.backend.repository.FavouritePlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FavouritePlacesService {

    @Autowired
    private FavouritePlaceRepository favouritePlaceRepository;

    @Autowired
    private WeatherService weatherService;

    public List<FavouritePlace> getForUser(String user) {
        return favouritePlaceRepository.getForUser(user);
    }

    public FavouritesWeatherResponse getForUserWithWeather(String user) {
        List<FavouritePlace> places = favouritePlaceRepository.getForUser(user);
        List<FavouriteWeatherEntry> entries = places.stream().map(place -> {
            WeatherLocationResponse response = weatherService.getCurrentWeather(new WeatherLocationRequest(place.getLat(), place.getLng()));
            return FavouriteWeatherEntry.builder().name(response.getName()).lat(response.getLat()).lng(response.getLng()).temperature(response.getTemperature()).build();
        }).collect(Collectors.toList());
        return new FavouritesWeatherResponse(entries);
    }

    public List<FavouritePlace> getInBoundingBox(Double minLat, Double maxLat, Double minLng, Double maxLng, String user) {
        return favouritePlaceRepository.getInBoundingBox(minLat, maxLat, minLng, maxLng, user);
    }

    public List<FavouritePlace> getInRadius(Double lat, Double lng, Double radius, String user) {
        return favouritePlaceRepository.getInBoundingBox(lat - radius, lat + radius, lng - radius, lng + radius, user);
    }

    public FavouritePlace insert(FavouritePlace favouritePlace) {
        return favouritePlaceRepository.saveAndFlush(favouritePlace);
    }

    public void deleteById(Long id) {
        favouritePlaceRepository.deleteById(id);
    }
}
