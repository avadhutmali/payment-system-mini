package com.avadhut.payment.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class FundAccountRequest {
    private UUID userId;
    private Long amount;

}
