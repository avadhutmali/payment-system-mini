package com.avadhut.payment.controller;

import com.avadhut.payment.dtos.TransactionResponse;
import com.avadhut.payment.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionRepository transactionRepository;

    @GetMapping("/{userId}")
    public List<TransactionResponse> getTransaction(@PathVariable UUID userId){
        return transactionRepository.findAll()
                .stream()
                .filter(tx -> tx.getPayeeAccountId().equals(userId)||
                               tx.getPayerAccountId().equals(userId)
                        )
                .map(tx -> new TransactionResponse(
                        tx.getId(),
                        tx.getAmount(),
                        tx.getStatus(),
                        tx.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}
