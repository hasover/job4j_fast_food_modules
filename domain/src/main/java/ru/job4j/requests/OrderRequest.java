package ru.job4j.requests;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class OrderRequest {
    private Map<Integer, Integer> dishes = new HashMap<>();
    private Integer accountId;
    private Integer customerId;
}
