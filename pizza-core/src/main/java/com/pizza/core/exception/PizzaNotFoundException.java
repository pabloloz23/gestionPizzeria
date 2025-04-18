package com.pizza.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PizzaNotFoundException extends RuntimeException {

    public PizzaNotFoundException(Long pizzaId) {
        super("Pizza no encontrada con ID: " + pizzaId);
    }
}