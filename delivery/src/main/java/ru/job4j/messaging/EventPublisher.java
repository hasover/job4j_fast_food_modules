package ru.job4j.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.Delivery;
import ru.job4j.Notification;
import ru.job4j.event.NotificationEvent;

@Service
public class EventPublisher {
    private final String notificationsTopic;
    private final KafkaTemplate<Integer, Object> kafkaTemplate;

    public EventPublisher(@Value("${kafka.topic.notifications}") String notificationsTopic,
                          KafkaTemplate<Integer, Object> kafkaTemplate) {
        this.notificationsTopic = notificationsTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void orderDelivered(Delivery delivery) {
        NotificationEvent notification = NotificationEvent.builder()
                .message(String.format("Your order %d has been delivered!", delivery.getOrderId()))
                .email(delivery.getEmail())
                .build();
        kafkaTemplate.send(notificationsTopic, notification);
    }
}
