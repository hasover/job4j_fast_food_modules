package ru.job4j.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.Account;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByCustomerId(Integer customerId);
}
