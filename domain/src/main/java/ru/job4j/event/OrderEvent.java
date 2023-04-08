package ru.job4j.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.OrderStatus;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private int orderId;
    private Map<Integer, Integer> dishes;
    private String address;
    private String email;
    private OrderStatus orderStatus;
}
