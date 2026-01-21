package com.avadhut.payment.service;

import com.avadhut.payment.entity.Account;
import com.avadhut.payment.entity.Transaction;
import com.avadhut.payment.entity.enums.TransactionStatus;
import com.avadhut.payment.repository.AccountRepository;
import com.avadhut.payment.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public Transaction makePayment(
            UUID payerId,
            UUID payeeId,
            Long amount,
            String idempotencyKey
    ) throws Exception {
        transactionRepository.findByIdempotencyKey(idempotencyKey).ifPresent(e->{
                   throw new IllegalStateException("");
        });

        Account payer = accountRepository.findByUserId(payerId).orElseThrow(()-> new IllegalArgumentException("Payer Account does not exits"));
        Account payee = accountRepository.findByUserId(payeeId).orElseThrow(()-> new IllegalArgumentException("Payee Account does not exits"));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setPayeeAccountId(payeeId);
        transaction.setPayerAccountId(payerId);
        transaction.setIdempotencyKey(idempotencyKey);
        transaction.setStatus(TransactionStatus.INITIATED);

        transactionRepository.save(transaction);

        if(payer.getBalance() < amount){
            transaction.setStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Insufficient Balance");
        }

        payer.setBalance(payer.getBalance()-amount);
        payee.setBalance(payee.getBalance()+amount);

        transaction.setStatus(TransactionStatus.SUCCESS);
        return transactionRepository.save(transaction);
    }

}
