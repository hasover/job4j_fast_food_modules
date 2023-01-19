package ru.job4j.filter;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimpleOrderFilter implements OrderFilter {
    @Override
    public boolean canAcceptTheOrder(List<Integer> dishIds) {
        return true;
    }
}
