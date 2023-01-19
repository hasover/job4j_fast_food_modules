package ru.job4j.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.job4j.OrderStatus;
import ru.job4j.service.OrderService;

@RestController
@RequestMapping("/status")
@RequiredArgsConstructor
public class OrderStatusController {
    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public OrderStatus getOrderStatus(@PathVariable Integer orderId) {
        return orderService.checkOrderStatus(orderId);
    }

    @PostMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateOrderStatus(@PathVariable Integer orderId, @RequestBody OrderStatus status) {
        orderService.updateOrderStatus(orderId, status);
    }

}
