package com.example.SpringOAuth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String indexHome() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}