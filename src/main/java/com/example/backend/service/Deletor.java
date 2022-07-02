package com.example.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Slf4j
@Configuration
@EnableScheduling
public class Deletor {

    @Autowired
    private UserReportService userReportService;

    @Autowired
    private WeatherDataService weatherDataService;

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteWeatherData() {
        log.info("Deleted weather data older than a day at:" + LocalDateTime.now());
        weatherDataService.deleteOldData();
    }

    @Scheduled(cron = "0 0 0/1 * * *")
    public void deleteReports() {
        log.info("Deleted reports older than three hours at:" + LocalDateTime.now());
        userReportService.deleteByHours(3);
    }

}
