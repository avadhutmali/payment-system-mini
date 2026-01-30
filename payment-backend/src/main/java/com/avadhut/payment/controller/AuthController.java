package com.avadhut.payment.controller;

import com.avadhut.payment.dtos.LoginRequest;
import com.avadhut.payment.dtos.LoginResponse;
import com.avadhut.payment.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        String token = jwtUtil.generateToken(request.getUserId(), request.getRole());

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
