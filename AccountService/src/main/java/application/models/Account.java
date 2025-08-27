package application.models;

import application.enums.AccountType;
import application.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String user_id;

    @Column(length = 20, unique = true, nullable = false)
    private String account_number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType account_type;

    @Column(columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
    private double balance;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "DEFAULT 'ACTIVE'")
    private Status status;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime created_at;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updated_at;
}
