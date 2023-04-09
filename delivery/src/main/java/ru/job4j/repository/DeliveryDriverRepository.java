package ru.job4j.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.DeliveryDriver;

public interface DeliveryDriverRepository extends JpaRepository<DeliveryDriver, Integer> {
}
