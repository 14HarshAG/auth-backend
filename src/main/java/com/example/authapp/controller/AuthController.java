package com.example.authapp.controller;
import com.example.authapp.dto.LoginRequest;
import com.example.authapp.model.User;
import com.example.authapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.register(user);
    }
    @PostMapping("/login")
public String login(@RequestBody LoginRequest request) {
    System.out.println("🔥 LOGIN API HIT"); // ADD THIS
    return authService.login(request.getEmail(), request.getPassword());
}
}