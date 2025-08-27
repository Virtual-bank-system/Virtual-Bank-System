package com.example.demo.application.services.impl;

import com.example.demo.apis.resources.*;
import com.example.demo.application.exceptions.InvalidUserException;
import com.example.demo.application.exceptions.UserAlreadyExistsException;
import com.example.demo.application.exceptions.UserNotFoundException;
import com.example.demo.application.models.User;
import com.example.demo.application.repos.UserRepo;
import com.example.demo.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(UserRegistration dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()
                || userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());

        User saved = userRepository.save(user);

        return new UserResponse(
                saved.getUserID(),
                saved.getUsername(),
                "User registered successfully"
        );
    }

    @Override
    public LoginResponse login(UserLogin dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new InvalidUserException());

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidUserException();
        }

        return new LoginResponse(
                user.getUserID(),
                user.getUsername()
        );
    }

    @Override
    public UserProfile getProfile(String userID) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userID + " not found.", "NOT_FOUND", 404));

        return new UserProfile(
                user.getUserID(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }
}
