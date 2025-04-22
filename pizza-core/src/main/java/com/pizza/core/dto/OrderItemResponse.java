package com.pizza.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponse {
    private String pizzaName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
}