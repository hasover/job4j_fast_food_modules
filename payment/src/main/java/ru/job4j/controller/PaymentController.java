package ru.job4j.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.Payment;
import ru.job4j.requests.PaymentRequest;
import ru.job4j.service.PaymentService;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping
    public ResponseEntity<Payment> payForTheOrder(@RequestBody PaymentRequest request) {
        try {
            Payment payment = paymentService.processPayment(request.getAccountId(), request.getTotal());
            return new ResponseEntity<>(payment, HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error("Error processing payment:{}",ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Payment> payForTheOrder(@PathVariable int id) {
        try {
            Payment payment = paymentService.refundPayment(id);
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error processing refund:{}", ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
