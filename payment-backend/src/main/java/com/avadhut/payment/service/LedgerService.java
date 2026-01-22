package com.avadhut.payment.service;

import com.avadhut.payment.entity.LedgerEntry;
import com.avadhut.payment.entity.enums.LedgerEntryType;
import com.avadhut.payment.repository.LedgerEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LedgerService {
    private final LedgerEntryRepository ledgerEntryRepository;

    public void recordDebit(UUID accountId, UUID transactionID, Long amount){
        LedgerEntry entry = new LedgerEntry();
        entry.setAccountId(accountId);
        entry.setAmount(amount);
        entry.setTransitionId(transactionID);
        entry.setEntryType(LedgerEntryType.DEBIT);

        ledgerEntryRepository.save(entry);
    }
    public void recordCredit(UUID accountId, UUID transactionID, Long amount){
        LedgerEntry entry = new LedgerEntry();
        entry.setAccountId(accountId);
        entry.setAmount(amount);
        entry.setTransitionId(transactionID);
        entry.setEntryType(LedgerEntryType.CREDIT);

        ledgerEntryRepository.save(entry);
    }



}
