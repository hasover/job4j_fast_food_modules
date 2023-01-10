package ru.job4j;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class Delivery {
    private int id;
    private String address;
    private int deliveryDriverId;

}
