package com.avadhut.payment.controller;

import com.avadhut.payment.dtos.CreateAccountRequest;
import com.avadhut.payment.entity.Account;
import com.avadhut.payment.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccountRequest request){
        Account account = accountService.createAccount(request.getUserID());
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAccount(@PathVariable UUID userId){
        Optional<Account> account = accountService.getAccountByUserId(userId);
        if(account.isEmpty())return ResponseEntity.badRequest().body("Invalid userId");

        return ResponseEntity.ok(account);
    }

}
