package com.example.authapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthappApplication {

	public static void main(String[] args) {
    System.out.println("MONGO URI = " + System.getenv("MONGODB_URI"));
    SpringApplication.run(AuthappApplication.class, args);
}

}
