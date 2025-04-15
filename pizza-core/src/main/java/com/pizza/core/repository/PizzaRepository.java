package com.pizza.core.repository;

import com.pizza.core.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    List<Pizza> findByNameContainingIgnoreCase(String name);
}