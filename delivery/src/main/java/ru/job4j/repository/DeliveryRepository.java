package ru.job4j.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.job4j.Delivery;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    List<Delivery> findAllByDeliveryDriverId(int id);

    Optional<Delivery> findDeliveryByOrderId(int orderId);

    @Query(value = "insert into deliveries_deliverydrivers values (?1, ?2)", nativeQuery = true)
    @Modifying
    void updateDeliverySetDriver(int orderId, int driverId);

}
