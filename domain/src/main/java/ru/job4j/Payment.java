package ru.job4j;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of = "id")
public class Payment {
    private int id;
    private double total;
    private LocalDateTime created = LocalDateTime.now();
}
