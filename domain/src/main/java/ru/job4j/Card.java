package ru.job4j;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Builder
public class Card {
    private int id;
    private double balance;
    private int customerId;
}
