package com.example.demo.apis.controllers;

import com.example.demo.apis.resources.*;
import com.example.demo.application.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegistration dto) {
        UserResponse response = userService.register(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody UserLogin dto) {
        LoginResponse response = userService.login(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userID}")
    public ResponseEntity<UserProfile> getProfile(@PathVariable String userID) {
        UserProfile profile = userService.getProfile(userID);
        return ResponseEntity.ok(profile);
    }
}
