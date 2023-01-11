package ru.job4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.Order;
import ru.job4j.repository.OrderRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void createOrder(Order order) {
        orderRepository.save(order);
    }

    public Optional<Order> findOrder(Integer id) {
        return orderRepository.findById(id);
    }

    public String checkOrderStatus(int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found!"));
        return order.getStatus();
    }

    public void updateOrderStatus(int orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found!"));
        order.setStatus(status);
        orderRepository.save(order);
    }
}
