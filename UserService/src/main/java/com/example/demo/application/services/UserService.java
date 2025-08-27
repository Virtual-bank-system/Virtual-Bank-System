package com.example.demo.application.services;

import com.example.demo.apis.resources.*;

public interface UserService {
    UserResponse register(UserRegistration dto);
    LoginResponse login(UserLogin dto);
    UserProfile getProfile(String userID);
}

