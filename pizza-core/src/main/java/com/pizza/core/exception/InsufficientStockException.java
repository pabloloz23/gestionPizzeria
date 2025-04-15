package com.pizza.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InsufficientStockException extends PizzaException {
    public InsufficientStockException(String pizzaName) {
        super("Insufficient stock for pizza: " + pizzaName);
    }
}