package ru.job4j.controller;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.event.OrderEvent;
import ru.job4j.service.KitchenService;

@Component
@RequiredArgsConstructor
public class KafkaController {

    private final KitchenService kitchenService;

    @KafkaListener(topics = "${kitchen.topic.in}")
    public void onOrderEvent(ConsumerRecord<Integer, OrderEvent> record) {
        kitchenService.processOrder(record.value());
    }

}
