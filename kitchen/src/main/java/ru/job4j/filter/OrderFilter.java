package ru.job4j.filter;

import java.util.List;

public interface OrderFilter {
    boolean canAcceptTheOrder(List<Integer> dishIds);
}
