package ru.job4j.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.Card;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Integer> {
    Optional<Card> findByCustomerId(Integer customerId);
}
