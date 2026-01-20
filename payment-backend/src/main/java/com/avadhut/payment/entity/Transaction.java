package com.avadhut.payment.entity;

import com.avadhut.payment.entity.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Table(
        name = "Transaction",
        indexes = @Index(
                name = "idx_transaction_idempotency",
                columnList = "idempotencyKey",
                unique = true
        )
)
public class Transaction {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID payerAccountId;

    @Column(nullable = false)
    private UUID payeeAccountId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false,unique = true)
    private String idempotencyKey;

    @Column(nullable = false,updatable = false)
    private Instant createdAt;

    @PrePersist
    public void onCreate(){
        this.createdAt = Instant.now();
    }

}
