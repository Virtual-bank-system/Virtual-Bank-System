package com.example.demo.apis.resources;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class UserProfile {

    private String userID;
    private String username;
    private String email;
    private String firstName;
    private String lastName;

}
