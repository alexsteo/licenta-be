package com.example.backend.repository;

import com.example.backend.model.db.UserReport;
import com.example.backend.model.db.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {


    @Query(value = "select w from WeatherData w where w.location = :location")
    List<WeatherData> getByLocation(String location);

    @Query(value = "select w from WeatherData w where w.location = :location and w.timestamp >= :current")
    List<WeatherData> getByLocationForFuture(String location, Timestamp current);

    @Modifying
    @Query(value = "delete from WeatherData w where w.timestamp <= :today")
    Integer deleteByDays(Timestamp today);

    @Modifying
    @Query(value = "delete from WeatherData w where w.location = :location")
    Integer deleteByLocation(String location);
}
