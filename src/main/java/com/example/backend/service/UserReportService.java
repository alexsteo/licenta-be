package com.example.backend.service;

import com.example.backend.model.db.UserReport;
import com.example.backend.model.shared.UserReportType;
import com.example.backend.repository.UserReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserReportService {

    @Autowired
    private UserReportRepository userReportRepository;

    public UserReport getById(Long id) {
        return userReportRepository.findById(id).orElse(new UserReport());
    }

    public List<UserReport> getByUser(String user) {
        return userReportRepository.getForUser(user);
    }

    public List<UserReport> getInBoundingBox(Double minLat, Double maxLat, Double minLng, Double maxLng) {
        return userReportRepository.getInBoundingBox(minLat, maxLat, minLng, maxLng);
    }

    public List<UserReport> getInRadius(Double lat, Double lng, Double radius) {
        return userReportRepository.getInBoundingBox(lat - radius, lat + radius, lng - radius, lng + radius);
    }

    public List<UserReport> getFromLastDays(Integer days) {
        Timestamp timestamp = Timestamp.from(Instant.now().minus(days, ChronoUnit.DAYS));
        return userReportRepository.getFromLastDays(timestamp);
    }

    public List<UserReport> getAllReports() {
        return userReportRepository.findAll();
    }

    public UserReport insert(UserReport userReport) {
        userReport.setTimestamp(Timestamp.from(Instant.now()));
        return userReportRepository.saveAndFlush(userReport);
    }

    public Boolean updateReportType(Long id, UserReportType type) {
        if(userReportRepository.updateType(id, type) > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public void deleteById(Long id) {
        userReportRepository.deleteById(id);
    }

    public Boolean deleteByDays(Integer days) {

        Timestamp timestamp = Timestamp.from(Instant.now().minus(days, ChronoUnit.DAYS));
        if(userReportRepository.deleteByDays(timestamp) > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public Boolean deleteByDaysAndType(Integer days, UserReportType type) {
        Timestamp timestamp = Timestamp.from(Instant.now().minus(days, ChronoUnit.DAYS));
        if(userReportRepository.deleteByDaysAndType(timestamp, type) > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
