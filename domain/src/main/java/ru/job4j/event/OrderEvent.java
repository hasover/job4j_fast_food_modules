package ru.job4j.event;

import lombok.Data;
import ru.job4j.OrderStatus;

import java.util.List;

@Data
public class OrderEvent {
    private int orderId;
    private List<Integer> dishIds;
    private OrderStatus orderStatus;
}
