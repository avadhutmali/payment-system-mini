package com.avadhut.payment.entity;

import com.avadhut.payment.entity.enums.LedgerEntryType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "ledger_entries")
@Data
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LedgerEntryType entryType;

    @PrePersist
    public void onCreate(){
        this.createdAt = Instant.now();
    }

}
