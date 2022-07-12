package com.example.backend.controller;

import com.example.backend.model.db.FavouritePlace;
import com.example.backend.model.dto.FavouritePlaceDto;
import com.example.backend.model.dto.responses.my.FavouriteWeatherEntry;
import com.example.backend.model.dto.responses.my.FavouritesWeatherResponse;
import com.example.backend.service.FavouritePlacesService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/favourites")
@Slf4j
public class FavouritePlacesController {

    @Autowired
    private FavouritePlacesService favouritePlacesService;

    @GetMapping("/{user}")
    public List<FavouritePlace> getFavouritesForUser(@PathVariable("user") String user) {
        return favouritePlacesService.getForUser(user);
    }

    @GetMapping("/weather/{user}")
    public FavouritesWeatherResponse getFavouritesForUserWithWeather(@PathVariable("user") String user) {
        return favouritePlacesService.getForUserWithWeather(user);
    }

    @PostMapping("/insert")
    public FavouriteWeatherEntry insertFavouritePlace(@RequestBody FavouritePlaceDto favouritePlace) {
        return favouritePlacesService.insert(favouritePlace);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        favouritePlacesService.deleteById(id);
    }

    @DeleteMapping("/city/{city}")
    public Integer deleteByCoordinates(@PathVariable("city") String city) {
        return favouritePlacesService.deleteByCity(city);
    }

    @DeleteMapping("/city/{city}/{user}")
    public Integer deleteByCityAndUser(@PathVariable("city") String city, @PathVariable("user") String user) {
        return favouritePlacesService.deleteByCityAndUser(city, user);
    }
}
