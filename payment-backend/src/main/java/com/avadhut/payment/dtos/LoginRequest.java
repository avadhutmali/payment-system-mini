package com.avadhut.payment.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class LoginRequest {
    private UUID userId;
    private String role;
}
