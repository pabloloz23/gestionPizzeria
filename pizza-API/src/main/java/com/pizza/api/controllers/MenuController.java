package com.pizza.api.controllers;

import com.pizza.core.exception.PizzaNotFoundException;
import com.pizza.core.model.Pizza;
import com.pizza.core.repository.PizzaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final PizzaRepository pizzaRepository;

    public MenuController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping
    public List<Pizza> getFullMenu() {
        return pizzaRepository.findAll();
    }

    @GetMapping("/{pizzaId}")
    public Pizza getPizzaDetails(@PathVariable Long pizzaId) {
        return pizzaRepository.findById(pizzaId)
                .orElseThrow(() -> new PizzaNotFoundException(pizzaId));
    }
}