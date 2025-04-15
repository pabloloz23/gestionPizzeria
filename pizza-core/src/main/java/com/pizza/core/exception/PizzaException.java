package com.pizza.core.exception;

public abstract class PizzaException extends RuntimeException {
    public PizzaException(String message) {
        super(message);
    }
}
