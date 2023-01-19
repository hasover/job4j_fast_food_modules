package ru.job4j.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.OrderHistory;

public interface KitchenRepository extends JpaRepository<OrderHistory, Integer> {
}
