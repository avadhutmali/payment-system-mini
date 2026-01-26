package com.avadhut.payment.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class BootstrapUserResponse {
    private UUID userId;
    private UUID accountId;
    private Long balance;
}
