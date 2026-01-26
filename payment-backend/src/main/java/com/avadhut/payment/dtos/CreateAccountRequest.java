package com.avadhut.payment.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateAccountRequest {
    private UUID userID;
}
