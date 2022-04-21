package com.example.backend.controller;

import com.example.backend.model.dto.requests.route.RouteRequest;
import com.example.backend.model.dto.responses.route.RouteWithWeatherResponse;
import com.example.backend.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/route")
@Slf4j
public class RouteController {

    @Autowired
    private RouteService routeService;

    public RouteController() {
    }

    @GetMapping("/{from}/{to}")
    public RouteWithWeatherResponse getRouteTo(@PathVariable("from") String from, @PathVariable("to") String to) {
        log.info(from);
        log.info(to);
        RouteRequest request = new RouteRequest(from, to);
        return routeService.getRoute(request);
    }
}
