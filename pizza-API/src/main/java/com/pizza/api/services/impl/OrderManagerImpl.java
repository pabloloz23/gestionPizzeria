package com.pizza.api.services.impl;

import com.pizza.api.services.AuthenticationService;
import com.pizza.api.services.PaymentProcessor;
import com.pizza.api.services.interfaces.OrderManager;
import com.pizza.core.dto.OrderItemRequest;
import com.pizza.core.dto.OrderRequest;
import com.pizza.core.dto.OrderResponse;
import com.pizza.core.exception.PizzaNotFoundException;
import com.pizza.core.model.*;
import com.pizza.core.repository.OrderRepository;
import com.pizza.core.repository.PizzaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderManagerImpl implements OrderManager {
    private final OrderRepository orderRepository;
    private final PizzaRepository pizzaRepository;
    private final PaymentProcessor paymentProcessor;
    private final AuthenticationService authenticationService;
    private final ModelMapper modelMapper;

@Override
@Transactional
public OrderResponse createOrder(OrderRequest request) {
    User user = authenticationService.getAuthenticatedUser();

    List<OrderItem> items = new ArrayList<>();
    if (request.getItems() != null) {
        for (OrderItemRequest item : request.getItems()) {
            items.add(createOrderItem(item));
        }
    }

    Order order = Order.builder()
            .user(user)
            .status(OrderStatus.PENDING)
            .items(items)
            .total(calculateTotal(items))
            .build();

    order = orderRepository.save(order);
    paymentProcessor.processPayment(order.getId().toString(), order.getTotal().doubleValue());

    return modelMapper.map(order, OrderResponse.class);
}

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new PizzaNotFoundException(id));
        return modelMapper.map(order, OrderResponse.class);
    }

    private BigDecimal calculateTotal(List<OrderItem> items) {
        return items.stream()
                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
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

    @Override
    public OrderResponse cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new PizzaNotFoundException(id));
        orderRepository.delete(order);
        return modelMapper.map(order, OrderResponse.class);
    }
}