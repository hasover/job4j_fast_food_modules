package ru.job4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.Account;
import ru.job4j.Payment;
import ru.job4j.repository.AccountRepository;
import ru.job4j.repository.PaymentRepository;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public Payment processPayment(int accountId, double total) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found!"));
        if (account.getBalance() < total) {
            throw new RuntimeException("Not enough balance!");
        }
        account.setBalance(account.getBalance() - total);
        Payment payment = new Payment();
        payment.setAccountId(accountId);
        payment.setTotal(total);
        paymentRepository.save(payment);
        return payment;
    }

    @Transactional
    public Payment refundPayment(int paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found!"));
        Account account = accountRepository.findById(payment.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found!"));
        account.setBalance(account.getBalance() + payment.getTotal());
        paymentRepository.delete(payment);
        return payment;
    }
}
