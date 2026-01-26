package com.avadhut.payment.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class PaymentRequest {
    private UUID payerUserId;
    private UUID payeeUserId;
    private Long amount;
    private String idempotencyKey;

}
