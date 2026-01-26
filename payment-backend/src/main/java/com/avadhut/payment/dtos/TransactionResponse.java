package com.avadhut.payment.dtos;

import com.avadhut.payment.entity.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class TransactionResponse {
    private UUID transactionId;
    private Long amount;
    private TransactionStatus status;
    private Instant createdAt;
}
