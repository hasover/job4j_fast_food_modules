package ru.job4j.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.OrderHistory;
import ru.job4j.OrderStatus;
import ru.job4j.event.OrderEvent;
import ru.job4j.filter.OrderFilter;
import ru.job4j.messaging.EventPublisher;
import ru.job4j.repository.KitchenRepository;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class KitchenService {

    private final KitchenRepository kitchenRepository;
    private final OrderFilter orderFilter;
    private final EventPublisher eventPublisher;

    public void processOrder(OrderEvent orderEvent) {
       if (orderFilter.canAcceptTheOrder(orderEvent.getDishes())) {
           try {
               acceptOrder(orderEvent);
           } catch (InterruptedException e) {
               log.debug("Error processing order:{}", e.getMessage());
               cancelOrder(orderEvent);
           }
       } else {
           cancelOrder(orderEvent);
       }
        kitchenRepository.save(
                new OrderHistory(orderEvent.getOrderId(), orderEvent.getOrderStatus()));
    }

    public void acceptOrder(OrderEvent orderEvent) throws InterruptedException {
        TimeUnit.SECONDS.sleep(15);
        orderEvent.setOrderStatus(OrderStatus.ORDER_COOKED);
        eventPublisher.orderCooked(orderEvent);
    }

    public void cancelOrder(OrderEvent orderEvent) {
        orderEvent.setOrderStatus(OrderStatus.ORDER_CANCELLED);
        eventPublisher.cancelledOrder(orderEvent);
    }
}
