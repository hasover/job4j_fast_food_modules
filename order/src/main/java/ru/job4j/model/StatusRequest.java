package ru.job4j.model;

import lombok.Data;

@Data
public class StatusRequest {
    private int orderId;
    private String status;
}
