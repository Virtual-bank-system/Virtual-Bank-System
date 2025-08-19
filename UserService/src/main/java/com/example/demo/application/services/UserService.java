package com.example.demo.application.services;

import com.example.demo.application.exceptions.ApplicationException;
import com.example.demo.apis.resources.UserCreateRequest;
import com.example.demo.apis.resources.UserCreateResponse;
import com.example.demo.apis.resources.UserUpdateRequest;
import jakarta.validation.Valid;
import java.util.List;

public interface UserService {

    UserCreateResponse createUser(@Valid UserCreateRequest user) throws ApplicationException;

    List<UserCreateResponse> getAllUsers();

    UserCreateResponse getUserById(String id);

    UserCreateResponse updateUser(String id, @Valid UserUpdateRequest updatedUser);

    void deleteUser(String id);
}