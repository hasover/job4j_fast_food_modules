package ru.job4j.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.Notification;
import ru.job4j.repository.NotificationRepository;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "messengers")
    public void onOrderCreated(ConsumerRecord<Integer, String> record) {
        Notification notification = new Notification();
        notification.setMessage(record.value());
        notificationRepository.save(notification);
    }
}
