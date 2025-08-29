package application.services;

import apis.resources.*;

public interface UserService {
    UserResponse register(UserRegistration dto);
    LoginResponse login(UserLogin dto);
    UserProfile getProfile(String userID);
}
