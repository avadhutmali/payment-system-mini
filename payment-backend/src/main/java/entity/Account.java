package entity;

import entity.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Data
public class Account {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false,unique = true)
    private UUID userId;

    @Column(nullable = false)
    private long balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    @Column(nullable = false,updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    public void onCreate(){
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PostPersist
    public void onUpdate(){
        this.updatedAt = Instant.now();
    }

}
