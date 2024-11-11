package com.example.orderservice.model;

import lombok.Data;

@Data
public class OrderedProduct {
    private final String productId;
    private final String productName;
    private int quantity;
    private double price;
}
