package com.example.backend.repository;

import com.example.backend.model.db.FavouritePlace;
import com.example.backend.model.db.UserReport;
import com.example.backend.model.shared.UserReportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public interface FavouritePlaceRepository extends JpaRepository<FavouritePlace, Long> {

    @Query("select f from FavouritePlace f where f.ofUser = ?1")
    List<FavouritePlace> getForUser(String user);

    @Query("select f from FavouritePlace f where f.lat between ?1 and ?2 and f.lng between ?3 and ?4 and f.ofUser = ?5")
    List<FavouritePlace> getInBoundingBox(Double minLat, Double maxLat, Double minLng, Double maxLng, String user);

    @Modifying
    @Query(value = "delete from FavouritePlace f where f.city = ?1")
    Integer deleteByCity(String city);
}
