package com.example.authapp.service;
import com.example.authapp.security.JwtUtil;
import com.example.authapp.model.User;
import com.example.authapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String register(User user) {

    // 🔥 Check if user already exists
    if (userRepository.findByEmail(user.getEmail()).isPresent()) {
        throw new RuntimeException("User already exists");
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole("ROLE_USER");

    userRepository.save(user);

    return "User registered successfully";
}
    public String login(String email, String password) {

    // Find user
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    // Check password
    if (!passwordEncoder.matches(password, user.getPassword())) {
        throw new RuntimeException("Invalid password");
    }

    // Generate JWT
    String token = jwtUtil.generateToken(user.getEmail(), user.getRole()); // pass role to token

    return token; // return token instead of message
}
}