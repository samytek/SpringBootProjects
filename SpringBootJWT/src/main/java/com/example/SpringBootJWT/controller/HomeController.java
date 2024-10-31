package com.example.SpringBootJWT.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/test")
    public String test() {
        logger.info("This is working message");
        return "Testing message";
    }
}
