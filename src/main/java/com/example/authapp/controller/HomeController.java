package com.example.authapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "<h1>Auth Backend is running! </h1>" +
                "<p>API Endpoints: <a href='/api/auth/'>/api/auth/</a></p>" +
                "<p>Documentation: <a href='/swagger-ui/index.html'>Swagger UI</a></p>";
    }
}
