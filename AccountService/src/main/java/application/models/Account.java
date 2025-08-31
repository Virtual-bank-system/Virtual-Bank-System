package application.models;

import application.enums.AccountType;
import application.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String user_id;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType account_type;

    @Column(columnDefinition = "DECIMAL(15,2)")
    private double balance=0.00;

    @Enumerated(EnumType.STRING)
    private Status status=Status.ACTIVE;

    @Column(updatable = false)
    private LocalDateTime created_at=LocalDateTime.now();

    private LocalDateTime updated_at=LocalDateTime.now();


}
