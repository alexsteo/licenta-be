package com.example.backend.controller;

import com.example.backend.model.db.FavouritePlace;
import com.example.backend.model.db.UserReport;
import com.example.backend.model.dto.responses.my.FavouritesWeatherResponse;
import com.example.backend.service.FavouritePlacesService;
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
    public List<FavouritePlace> getReportsForUser(@PathVariable("user") String user) {
        return favouritePlacesService.getForUser(user);
    }

    @GetMapping("/weather/{user}")
    public FavouritesWeatherResponse getReportsForUserWithWeather(@PathVariable("user") String user) {
        return favouritePlacesService.getForUserWithWeather(user);
    }

    @PostMapping("/insert")
    public FavouritePlace insertFavouritePlace(@RequestBody FavouritePlace favouritePlace) {
        return favouritePlacesService.insert(favouritePlace);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        favouritePlacesService.deleteById(id);
    }
}
