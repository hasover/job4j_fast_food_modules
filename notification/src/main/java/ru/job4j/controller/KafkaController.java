package ru.job4j.controller;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.service.NotificationService;

@Component
@RequiredArgsConstructor
public class KafkaController {

    private final NotificationService notificationService;

    @KafkaListener(topics = "messengers")
    public void onOrderEvent(ConsumerRecord<Integer, String> record) {
        notificationService.createNewNotification(record.value());
    }
}
