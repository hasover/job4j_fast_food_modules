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
import ru.job4j.repository.KitchenRepository;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class KitchenService {

    private final KitchenRepository kitchenRepository;
    private final KafkaTemplate<Integer, Object> template;
    private final OrderFilter orderFilter;
    @Value("${kitchen.topic.out}")
    private String kitchenTopicOut;

    public void processOrder(OrderEvent orderEvent) {
       if (orderFilter.canAcceptTheOrder(orderEvent.getDishIds())) {
           try {
               acceptOrder(orderEvent);
           } catch (InterruptedException e) {
               log.debug("Error processing order:{e}", e);
               cancelOrder(orderEvent);
           }
       } else {
           cancelOrder(orderEvent);
       }
        template.send(kitchenTopicOut, orderEvent);
    }

    public void acceptOrder(OrderEvent orderEvent) throws InterruptedException {
        TimeUnit.MINUTES.sleep(1);
        kitchenRepository.save(
                new OrderHistory(orderEvent.getOrderId(), OrderStatus.ORDER_COMPLETED));
        orderEvent.setOrderStatus(OrderStatus.ORDER_COMPLETED);
    }

    public void cancelOrder(OrderEvent orderEvent) {
        kitchenRepository.save(
                new OrderHistory(orderEvent.getOrderId(), OrderStatus.ORDER_CANCELLED));
        orderEvent.setOrderStatus(OrderStatus.ORDER_CANCELLED);
    }
}
