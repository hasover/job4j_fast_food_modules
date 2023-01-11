package ru.job4j.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.Card;
import ru.job4j.service.CardService;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping("/{customerId}")
    public Card findCustomerCard(@PathVariable Integer customerId) {
        return cardService.findCustomerCard(customerId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found!"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCard(@RequestBody Card card) {
        cardService.buyCard(card.getCustomerId(), card.getBalance());
    }
}
