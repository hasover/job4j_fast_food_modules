package ru.job4j;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class DeliveryDriver {
    private int id;
    private String name;
    private String phoneNumber;
}
