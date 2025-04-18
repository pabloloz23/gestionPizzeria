package com.pizza.api.controllers;

import com.pizza.api.services.PaymentProcessor;
import com.pizza.core.dto.PaymentRequest;
import com.pizza.core.dto.PaymentResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentProcessor paymentProcessor;

    public PaymentController(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    @PostMapping("/process")
    public PaymentResponse processPayment(@RequestBody PaymentRequest request) {
        return paymentProcessor.processPayment(request);
    }

    @GetMapping("/status/{transactionId}")
    public PaymentResponse checkPaymentStatus(@PathVariable String transactionId) {
        return paymentProcessor.getPaymentStatus(transactionId);
    }
}