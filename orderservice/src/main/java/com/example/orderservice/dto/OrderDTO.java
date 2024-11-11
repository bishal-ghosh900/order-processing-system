package com.example.orderservice.dto;

import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderedProduct;

import java.util.List;

public record OrderDTO(String orderId, String email, List<OrderedProduct> items, double totalPrice) {
    public static OrderDTO of(Order order) {
        return new OrderDTO(
                order.getOrderId(),
                order.getEmail(),
                order.getItems(),
                order.getTotalPrice()
        );
    }
}
