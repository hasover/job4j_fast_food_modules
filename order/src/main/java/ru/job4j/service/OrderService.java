package ru.job4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.Order;
import ru.job4j.OrderStatus;
import ru.job4j.event.OrderEvent;
import ru.job4j.repository.OrderRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<Integer, Object> kafkaTemplate;

    public void createOrder(Order order) {
        orderRepository.save(order);
        kafkaTemplate.send("messengers",
                String.format("Your order #%d has been placed", order.getId()));

        OrderEvent event = new OrderEvent();
        event.setOrderId(order.getId());
        event.setDishIds(order.getDishIds());
        event.setOrderStatus(OrderStatus.ORDER_CREATED);
        kafkaTemplate.send("preorder", event);
    }

    public Optional<Order> findOrder(Integer id) {
        return orderRepository.findById(id);
    }

    public OrderStatus checkOrderStatus(int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found!"));
        return order.getStatus();
    }

    public void updateOrderStatus(int orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found!"));
        order.setStatus(status);
        orderRepository.save(order);
    }
}
