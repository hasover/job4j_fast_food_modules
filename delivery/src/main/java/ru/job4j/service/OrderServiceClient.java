package ru.job4j.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.OrderStatus;

@Service
public class OrderServiceClient {

    private final String url;
    private final RestTemplate restTemplate;

    public OrderServiceClient(@Value("${orders.api.url}") String url,
                              RestTemplateBuilder builder) {
        this.url = url;
        this.restTemplate = builder.build();
    }

    public void updateOrderStatus(int orderId, OrderStatus status) {
        restTemplate.postForEntity(String.format("%s/%d/status", url, orderId),
                status, Void.class);
    }
}
