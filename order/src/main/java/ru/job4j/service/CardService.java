package ru.job4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.Account;
import ru.job4j.repository.CardRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public Account buyCard(int customerId, double sum) {
        Account card = Account
                .builder()
                .customerId(customerId)
                .balance(sum)
                .build();
        cardRepository.save(card);
        return card;
    }

    public Optional<Account> findCustomerCard(int customerId) {
        return cardRepository.findByCustomerId(customerId);
    }
}
