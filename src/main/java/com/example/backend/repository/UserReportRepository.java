package com.example.backend.repository;

import com.example.backend.model.db.UserReport;
import com.example.backend.model.shared.UserReportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public interface UserReportRepository extends JpaRepository<UserReport, Long> {

    @Query("select u from UserReport u where u.reporter = ?1")
    List<UserReport> getForUser(String user);

    @Query("select u from UserReport u where u.lat > ?1 and u.lat < ?2 and u.lng > ?3 and u.lng < ?4")
    List<UserReport> getInBoundingBox(Double minLat, Double maxLat, Double minLng, Double maxLng);

    @Query("select u from UserReport u where u.lat between ?1 and ?2 and u.lng between ?3 and ?4 and u.timestamp > ?5")
    List<UserReport> getInBoundingBoxSince(Double minLat, Double maxLat, Double minLng, Double maxLng, Timestamp timestamp);

    @Query(value = "select u from UserReport u where u.timestamp > ?1")
    List<UserReport> getFromLastDays(Timestamp from);

    @Modifying
    @Query("update UserReport u set u.type = ?2 where u.id = ?1")
    Integer updateType(Long id, UserReportType type);

    @Modifying
    @Query(value = "delete from UserReport u where u.timestamp > ?1")
    Integer deleteByTimestamp(Timestamp from);

    @Modifying
    @Query(value = "delete from UserReport u where u.type = ?2 and u.timestamp > ?1")
    Integer deleteByDaysAndType(Timestamp from, UserReportType type);
}
