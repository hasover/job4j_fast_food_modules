package ru.job4j.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.event.OrderEvent;
import ru.job4j.service.KitchenService;

@Service
@RequiredArgsConstructor
public class OrderEventHandler {
    private final KitchenService kitchenService;

    @KafkaListener(topics = "${kafka.topic.orders}")
    public void handleNewOrder(OrderEvent event) {
        kitchenService.processOrder(event);
    }
}
