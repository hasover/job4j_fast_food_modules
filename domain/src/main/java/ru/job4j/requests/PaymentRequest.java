package ru.job4j.requests;

import lombok.Data;

@Data
public class PaymentRequest {
    private int accountId;
    private double total;
}
