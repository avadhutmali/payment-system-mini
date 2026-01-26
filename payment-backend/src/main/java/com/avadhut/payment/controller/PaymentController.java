package com.avadhut.payment.controller;

import com.avadhut.payment.dtos.PaymentRequest;
import com.avadhut.payment.entity.Transaction;
import com.avadhut.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Transaction> makePayment(@RequestBody PaymentRequest request) throws Exception {
        Transaction transaction = paymentService.makePayment(request.getPayerUserId(),request.getPayeeUserId(),request.getAmount(),request.getIdempotencyKey());
        return ResponseEntity.ok(transaction);
    }

}
