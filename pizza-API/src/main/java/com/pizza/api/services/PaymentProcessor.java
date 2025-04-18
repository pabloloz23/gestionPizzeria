package com.pizza.api.services;

import org.springframework.stereotype.Service;

@Service
public class PaymentProcessor {

    public boolean processPayment(String orderId, double amount) {
        // Simular un proceso de pago real
        System.out.println("Procesando pago de $" + amount + " para la orden ID: " + orderId);
        return true;
    }
}
