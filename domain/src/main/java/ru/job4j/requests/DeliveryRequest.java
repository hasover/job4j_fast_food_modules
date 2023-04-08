package ru.job4j.requests;

import lombok.Data;

@Data
public class DeliveryRequest {
    private int orderId;
    private int deliveryDriverId;
}
