package ru.job4j.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.StatusRequest;
import ru.job4j.service.OrderService;

@RestController
@RequestMapping("/status")
@RequiredArgsConstructor
public class OrderStatusController {
    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public String getOrderStatus(@PathVariable Integer orderId) {
        return orderService.checkOrderStatus(orderId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateOrderStatus(@RequestBody StatusRequest request) {
        orderService.updateOrderStatus(request.getOrderId(), request.getStatus());
    }

}
