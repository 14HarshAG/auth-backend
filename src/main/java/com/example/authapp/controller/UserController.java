package com.example.authapp.controller;

import com.example.authapp.model.User;
import com.example.authapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    // ✅ Test API
    @GetMapping("/test")
    public String test() {
        return "Protected API working";
    }

    // 🔥 NEW: Get all users
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}