package ru.job4j.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}