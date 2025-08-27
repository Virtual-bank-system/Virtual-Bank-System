package com.example.demo.apis.resources;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class UserResponse {

    private String userID;
    private String username;
    private String message;

}
