package com.avadhut.payment.service;


import com.avadhut.payment.entity.Account;
import com.avadhut.payment.entity.enums.AccountStatus;
import com.avadhut.payment.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public Account createAccount(UUID userId) {
        Optional<Account> existing = accountRepository.findByUserId(userId);

        if (existing.isPresent()) {
            return existing.get();
        }

        Account account = new Account();
        account.setUserId(userId);
        account.setBalance(0L);
        account.setStatus(AccountStatus.ACTIVE);

        return accountRepository.save(account);
    }

    public Optional<Account> getAccountByUserId(UUID id) {
        return accountRepository.findByUserId(id);
    }
}
