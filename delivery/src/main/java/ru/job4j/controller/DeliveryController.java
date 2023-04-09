package ru.job4j.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.Delivery;
import ru.job4j.requests.DeliveryRequest;
import ru.job4j.service.DeliveryService;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
@Slf4j
public class DeliveryController {
    private final DeliveryService deliveryService;

    @GetMapping
    public List<Delivery> findOrdersToBeDelivered() {
        return deliveryService.findOrdersToBeDelivered();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void acceptOrderForDelivery(@RequestBody DeliveryRequest request) {
        try {
            deliveryService.acceptOrderForDelivery(request);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The order has already been accepted by another delivery driver!");
        }
    }

    @PatchMapping("/{id}")
    public void markOrderAsDelivered(@PathVariable int id) {
        deliveryService.markOrderAsDelivered(id);
    }
}
