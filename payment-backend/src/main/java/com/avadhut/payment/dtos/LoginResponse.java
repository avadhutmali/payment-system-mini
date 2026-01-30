package com.avadhut.payment.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {
    private String token;
}
