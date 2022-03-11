package com.example.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/")
@Slf4j
public class Controller {

    @Autowired
    public Controller(){}

    @GetMapping()
    @ResponseBody
    public String testConnectionAndResponse() {
        log.info("test");
        return "SUCCESS";
    }
}
