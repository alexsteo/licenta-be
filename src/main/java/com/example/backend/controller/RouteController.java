package com.example.backend.controller;

import com.example.backend.model.dto.requests.route.RouteRequest;
import com.example.backend.model.dto.responses.route.RouteWithWeatherResponse;
import com.example.backend.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Scanner;

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

    @GetMapping("/set")
    public String get() {
        String ret = "";
        try {
            InputStream reader = new ClassPathResource("route.txt").getInputStream();
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                ret = ret.concat(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
