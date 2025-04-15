package com.pizza.api.services.impl;

import com.pizza.api.services.interfaces.OrderManager;
import com.pizza.core.dto.OrderItemRequest;
import com.pizza.core.dto.OrderRequest;
import com.pizza.core.dto.OrderResponse;
import com.pizza.core.model.*;
import com.pizza.core.repository.OrderRepository;
import com.pizza.core.repository.PizzaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderManagerImpl implements OrderManager {
    private final OrderRepository orderRepository;
    private final PizzaRepository pizzaRepository;
    private final PaymentProcessor paymentProcessor;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        User user = getAuthenticatedUser();

        List<OrderItem> items = request.getItems().stream()
                .map(this::createOrderItem)
                .toList();

        Order order = Order.builder()
                .user(user)
                .status(OrderStatus.PENDING)
                .items(items)
                .total(calculateTotal(items))
                .build();

        order = orderRepository.save(order);
        paymentProcessor.processPayment(order);

        return modelMapper.map(order, OrderResponse.class);
    }

    private OrderItem createOrderItem(OrderItemRequest itemRequest) {
        Pizza pizza = pizzaRepository.findById(itemRequest.getPizzaId())
                .orElseThrow(() -> new PizzaNotFoundException(itemRequest.getPizzaId()));

        return OrderItem.builder()
                .pizza(pizza)
                .quantity(itemRequest.getQuantity())
                .unitPrice(pizza.getPrice())
                .build();
    }

    // Métodos auxiliares...
}