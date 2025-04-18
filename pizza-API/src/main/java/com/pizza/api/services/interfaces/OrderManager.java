package com.pizza.api.services.interfaces;

import com.pizza.core.dto.OrderRequest;
import com.pizza.core.dto.OrderResponse;

public interface OrderManager {

    OrderResponse createOrder(OrderRequest request);

    OrderResponse getOrderById(Long id);

    OrderResponse cancelOrder(Long id);


}
