package ru.job4j.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {
    private final String dishUpdatesTopic;
    private final KafkaTemplate<Integer, Object> kafkaTemplate;

    public EventPublisher(@Value("${kafka.topic.dish-updates}") String dishUpdatesTopic,
                          KafkaTemplate<Integer, Object> kafkaTemplate) {
        this.dishUpdatesTopic = dishUpdatesTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void dishUpdated() {
        kafkaTemplate.send(dishUpdatesTopic, "Dish repository updated!");
    }
}
