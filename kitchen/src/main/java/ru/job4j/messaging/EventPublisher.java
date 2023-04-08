package ru.job4j.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.event.NotificationEvent;
import ru.job4j.event.OrderEvent;

@Service
public class EventPublisher {
    private final String notificationsTopic;
    private final String cookedOrders;
    private final String cancelledOrders;
    private final KafkaTemplate<Integer, Object> kafkaTemplate;

    public EventPublisher(@Value("${kafka.topic.notifications}") String notificationsTopic,
                          @Value("${kafka.topic.cooked-orders}") String cookedOrders,
                          @Value("${kafka.topic.cancelled-orders}") String cancelledOrders,
                          KafkaTemplate<Integer, Object> kafkaTemplate) {
        this.notificationsTopic = notificationsTopic;
        this.cookedOrders = cookedOrders;
        this.cancelledOrders = cancelledOrders;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void orderCooked(OrderEvent event) {
        String message = String.format("Your order %d has been handed over to a delivery service",
        event.getOrderId());
        NotificationEvent notificationEvent = NotificationEvent.builder()
                .message(message)
                .email(event.getEmail())
                .build();
        kafkaTemplate.send(cookedOrders, event);
        kafkaTemplate.send(notificationsTopic, notificationEvent);

    }

    public void cancelledOrder(OrderEvent event) {
        String message = String.format("Your order %d can't be completed",
                event.getOrderId());
        NotificationEvent notificationEvent = NotificationEvent.builder()
                .message(message)
                .email(event.getEmail())
                .build();
        kafkaTemplate.send(cancelledOrders, event);
        kafkaTemplate.send(notificationsTopic, notificationEvent);
    }
}
