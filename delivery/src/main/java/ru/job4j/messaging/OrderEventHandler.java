package ru.job4j.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.event.OrderEvent;
import ru.job4j.service.DeliveryService;

@Service
@RequiredArgsConstructor
public class OrderEventHandler {

    private final DeliveryService deliveryService;

    @KafkaListener(topics = "${kafka.topic.cooked-orders}")
    public void handleNewOrder(OrderEvent event) {
        deliveryService.newOrderDelivery(event);
    }
}
