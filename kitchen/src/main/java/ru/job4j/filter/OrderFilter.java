package ru.job4j.filter;

import java.util.List;
import java.util.Map;

public interface OrderFilter {
    boolean canAcceptTheOrder(Map<Integer, Integer> dishIds);
}
