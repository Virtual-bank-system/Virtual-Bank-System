package application.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userID;

    @Column (unique = true, nullable = false)
    private String username;

    @Column (nullable = false)
    private String password;

    @Column (unique = true, nullable = false)
    private String email;

    @Column (nullable = false)
    private String firstName;

    @Column (nullable = false)
    private String lastName;

    @Column(updatable = false)
    private LocalDateTime created_at = LocalDateTime.now();
}

