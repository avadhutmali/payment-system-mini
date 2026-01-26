package com.avadhut.payment.controller;

import com.avadhut.payment.dtos.BootstrapUserResponse;
import com.avadhut.payment.dtos.FundAccountRequest;
import com.avadhut.payment.entity.Account;
import com.avadhut.payment.service.AccountService;
import com.avadhut.payment.service.LedgerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/internal")
@RestController
public class InternalBootstrapController {

    //This is Testing purpose to simulate the real issues

    private final AccountService accountService;
    private final LedgerService ledgerService;

    @PostMapping("/bootstrap-user")
    public BootstrapUserResponse bootStrapUser(){
        UUID userId = UUID.randomUUID();
        Account account = accountService.createAccount(userId);

        return new BootstrapUserResponse(userId,account.getId(),account.getBalance());
    }


    @PostMapping("/bootstrap-fund")
    @Transactional
    public Account fundAccount(@RequestBody FundAccountRequest request){
        Account account = accountService.getAccountByUserId(request.getUserId()).orElseThrow(()-> new IllegalArgumentException("Account Not FOund"));
        Long amount = request.getAmount();

        if(amount<=0){
            throw  new IllegalArgumentException("Amount must be positive");
        }

        //Fake transaction ID for the testing
        UUID transactionId = UUID.randomUUID();

        ledgerService.recordCredit(
                account.getId(),
                transactionId,
                amount
        );

        account.setBalance(account.getBalance()+amount);
        return account;
    }
}
