package ru.job4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.Delivery;
import ru.job4j.OrderStatus;
import ru.job4j.event.OrderEvent;
import ru.job4j.messaging.EventPublisher;
import ru.job4j.repository.DeliveryRepository;
import ru.job4j.requests.DeliveryRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final OrderServiceClient orderServiceClient;
    private final EventPublisher eventPublisher;

    public void newOrderDelivery(OrderEvent event) {
        Delivery delivery = Delivery.builder()
                .orderId(event.getOrderId())
                .address(event.getAddress())
                .email(event.getEmail())
                .orderStatus(OrderStatus.ORDER_CREATED)
                .build();
        deliveryRepository.save(delivery);
    }

    public List<Delivery> findOrdersToBeDelivered() {
        return deliveryRepository.findAllByDeliveryDriverId(0);
    }

    @Transactional
    public void acceptOrderForDelivery(DeliveryRequest request) {
        Delivery delivery = deliveryRepository.findDeliveryByOrderId(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found!"));
        deliveryRepository.updateDeliverySetDriver(request.getOrderId(),
                request.getDeliveryDriverId());
        delivery.setDeliveryDriverId(request.getDeliveryDriverId());
    }

    @Transactional
    public void markOrderAsDelivered(int orderId) {
        Delivery delivery = deliveryRepository.findDeliveryByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found!"));
        delivery.setOrderStatus(OrderStatus.ORDER_COMPLETED);
        orderServiceClient.updateOrderStatus(orderId, OrderStatus.ORDER_COMPLETED);
        eventPublisher.orderDelivered(delivery);
    }
}
