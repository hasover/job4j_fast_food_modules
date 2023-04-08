package ru.job4j.filter;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SimpleOrderFilter implements OrderFilter {
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public boolean canAcceptTheOrder(Map<Integer, Integer> dishIds) {
        return counter.getAndIncrement() % 2 == 0;
    }
}
