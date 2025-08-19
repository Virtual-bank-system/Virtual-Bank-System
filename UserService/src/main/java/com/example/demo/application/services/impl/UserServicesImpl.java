package com.example.demo.application.services.impl;

import com.example.demo.application.models.User;
import com.example.demo.application.exceptions.UserAlreadyExistsException;
import com.example.demo.application.exceptions.UserNotFoundException;
import com.example.demo.application.repos.UserRepo;
import com.example.demo.application.services.UserService;
import com.example.demo.application.services.mappers.UserMapper;
import com.example.demo.apis.resources.UserCreateRequest;
import com.example.demo.apis.resources.UserCreateResponse;
import com.example.demo.apis.resources.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserService {

        @Autowired
        private UserRepo userRepo;

        @Autowired
        private UserMapper userMapper;

        public UserCreateResponse createUser(UserCreateRequest request) {
        Optional<User> userEntity = userRepo.findByEmailOrUsername(request.getEmail(),request.getUsername());
        if (!userEntity.isEmpty()) {
            throw new UserAlreadyExistsException();
        }
        User user = userMapper.mapToEntity(request);
        User savedUser = userRepo.save(user);
        return userMapper.toResponse(savedUser);
    }

        public List<UserCreateResponse> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

        public UserCreateResponse getUserById(String id) {
        User user = userRepo.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return userMapper.toResponse(user);
    }

        public UserCreateResponse updateUser(String id, UserUpdateRequest request) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(UserNotFoundException::new);

        if (request.getUsername() != null) existingUser.setUsername(request.getUsername());
        if (request.getEmail() != null) existingUser.setEmail(request.getEmail());
        if (request.getFirstName() != null) existingUser.setFirstName(request.getFirstName());
        if (request.getLastName() != null) existingUser.setLastName(request.getLastName());

        User savedUser = userRepo.save(existingUser);
        return userMapper.toResponse(savedUser);
    }


        public void deleteUser(String id) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userRepo.delete(existingUser);
    }
}


