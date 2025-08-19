package com.example.demo.application.services.mappers;

import com.example.demo.application.models.User;
import com.example.demo.apis.resources.UserCreateRequest;
import com.example.demo.apis.resources.UserCreateResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapToEntity(UserCreateRequest dto);
    UserCreateResponse toResponse(User entity);
}
