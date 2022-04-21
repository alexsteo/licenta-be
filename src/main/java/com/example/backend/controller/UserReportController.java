package com.example.backend.controller;

import com.example.backend.model.db.UserReport;
import com.example.backend.model.dto.requests.userReports.UserReportBoundingBoxRequest;
import com.example.backend.model.dto.responses.my.UserReportForBoundingBoxResponse;
import com.example.backend.service.UserReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/report")
@Slf4j
public class UserReportController {

    @Autowired
    private UserReportService userReportService;

    public UserReportController() {
    }

    @GetMapping("/")
    public List<UserReport> getAllReports() {
        return userReportService.getAllReports();
    }

    @PostMapping("/merged")
    public UserReportForBoundingBoxResponse getInBoundingBoxMerged(@RequestBody UserReportBoundingBoxRequest request) {
        return userReportService.getReportsInBoundingBoxMerged(request);
    }

    @GetMapping("/{id}")
    public UserReport getReportById(@PathVariable("id") Long id) {
        return userReportService.getById(id);
    }

    @PostMapping("/insert")
    public UserReport insertReport(@RequestBody UserReport report) {
        return userReportService.insert(report);
    }

    @PutMapping("/update/{id}")
    public Boolean updateReport(@PathVariable("id") Long id, @RequestBody UserReport report) {
        return userReportService.updateReportType(id, report.getType());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        userReportService.deleteById(id);
    }

    @DeleteMapping("/delete/{days}")
    public Boolean deleteByDays(@PathVariable("days") Integer days) {
        return userReportService.deleteByDays(days);
    }

    @DeleteMapping("/delete/type/{days}")
    public Boolean deleteByDays(@PathVariable("days") Integer days, @RequestBody UserReport report) {
        return userReportService.deleteByDaysAndType(days, report.getType());
    }
}
