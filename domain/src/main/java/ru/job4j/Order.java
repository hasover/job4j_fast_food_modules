package ru.job4j;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
public class Order {
    private int id;
    private List<Integer> dishIds;
    private int paymentId;
    private int kitchenId;
    private int deliveryId;
}
