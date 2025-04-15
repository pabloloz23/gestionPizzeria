package com.pizza.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidOrderStatusException extends PizzaException {
    public InvalidOrderStatusException(String message) {
        super(message);
    }
}
