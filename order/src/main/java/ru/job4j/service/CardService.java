package ru.job4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.Card;
import ru.job4j.repository.CardRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public Card buyCard(int customerId, double sum) {
        Card card = Card
                .builder()
                .customerId(customerId)
                .balance(sum)
                .build();
        cardRepository.save(card);
        return card;
    }

    public Optional<Card> findCustomerCard(int customerId) {
        return cardRepository.findByCustomerId(customerId);
    }
}
