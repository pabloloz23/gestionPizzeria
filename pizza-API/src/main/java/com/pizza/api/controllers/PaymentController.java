package com.pizza.api.controllers;

import com.pizza.api.services.PaymentProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentProcessor paymentProcessor;

    public PaymentController(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<String> processPayment(@PathVariable String orderId, @RequestParam double amount) {
        boolean success = paymentProcessor.processPayment(orderId, amount);
        if (success) {
            return ResponseEntity.ok("Pago procesado con éxito");
        } else {
            return ResponseEntity.status(500).body("Error al procesar el pago");
        }
    }
}
