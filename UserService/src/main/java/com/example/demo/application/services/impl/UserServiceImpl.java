package com.example.demo.application.services.impl;

import com.example.demo.apis.resources.*;
import com.example.demo.application.exceptions.InvalidUserException;
import com.example.demo.application.exceptions.UserAlreadyExistsException;
import com.example.demo.application.exceptions.UserNotFoundException;
import com.example.demo.application.models.User;
import com.example.demo.application.repos.UserRepo;
import com.example.demo.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public UserResponse register(UserRegistration dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()
                || userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword()); // ⚠ no encoding
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());

        User saved = userRepository.save(user);

        return new UserResponse(
                saved.getId(),
                saved.getUsername(),
                "User registered successfully"
        );
    }

    @Override
    public LoginResponse login(UserLogin dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new InvalidUserException());

        // ⚠ plain text comparison
        if (!dto.getPassword().equals(user.getPassword())) {
            throw new InvalidUserException();
        }

        return new LoginResponse(
                user.getId(),
                user.getUsername()
        );
    }

    @Override
    public UserProfile getProfile(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found.", "NOT_FOUND", 404));

        return new UserProfile(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }
}
