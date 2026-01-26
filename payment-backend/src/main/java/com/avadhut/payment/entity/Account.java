package com.avadhut.payment.entity;

import com.avadhut.payment.entity.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
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

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }
    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public long getBalance() {
        return balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
