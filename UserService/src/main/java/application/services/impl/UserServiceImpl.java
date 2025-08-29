package application.services.impl;

import apis.resources.*;
import application.exceptions.InvalidUserException;
import application.exceptions.UserAlreadyExistsException;
import application.exceptions.UserNotFoundException;
import application.models.Users;
import application.repos.UserRepo;
import application.services.UserService;
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

        Users users = new Users();
        users.setUsername(dto.getUsername());
        users.setPassword(passwordEncoder.encode(dto.getPassword()));
        users.setEmail(dto.getEmail());
        users.setFirstName(dto.getFirstName());
        users.setLastName(dto.getLastName());

        Users saved = userRepository.save(users);

        return new UserResponse(
                saved.getUserID(),
                saved.getUsername(),
                "User registered successfully"
        );
    }

    @Override
    public LoginResponse login(UserLogin dto) {
        Users users = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new InvalidUserException());

        if (!passwordEncoder.matches(dto.getPassword(), users.getPassword())) {
            throw new InvalidUserException();
        }

        return new LoginResponse(
                users.getUserID(),
                users.getUsername()
        );
    }

    @Override
    public UserProfile getProfile(String userID) {
        Users users = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userID + " not found.", "NOT_FOUND", 404));

        return new UserProfile(
                users.getUserID(),
                users.getUsername(),
                users.getEmail(),
                users.getFirstName(),
                users.getLastName()
        );
    }
}
