package ru.job4j.controller;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.event.OrderEvent;
import ru.job4j.service.OrderService;

@Component
@RequiredArgsConstructor
public class KafkaController {

    private final OrderService orderService;

    @KafkaListener(topics = "cooked_order")
    public void onOrderEvent(ConsumerRecord<Integer, OrderEvent> record) {
        OrderEvent event = record.value();
        orderService.updateOrderStatus(event.getOrderId(), event.getOrderStatus());
    }
}
