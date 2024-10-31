package com.example.API_Gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String TestMethodApiGateway() {
        return "Hello From TestController Api-Gateway";
    }
}
