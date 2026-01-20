package com.avadhut.payment.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "ledger_entries")
public class LedgerEntry {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable=false)
    private UUID accountId;

    @Column(nullable = false)
    private UUID transitionId;

    @Column(nullable = false)
    private long amount;

    @Column(nullable = false)
    private Instant createdAt;

    @PrePersist
    public void onCreate(){
        this.createdAt = Instant.now();
    }

}
