package com.example.backend.mapper;

import com.example.backend.model.db.FavouritePlace;
import com.example.backend.model.dto.FavouritePlaceDto;

public class FavouritePlaceMapper {

    public static FavouritePlace mapDtoToEntity(FavouritePlaceDto dto) {
        FavouritePlace entity = new FavouritePlace();
        entity.setLat(dto.getLat());
        entity.setLng(dto.getLng());
        entity.setOfUser(dto.getUsername());
        entity.setCity(dto.getCity());
        return entity;
    }
}
