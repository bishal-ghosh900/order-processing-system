package com.example.orderservice.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Order {
    private String orderId;
    private String email;
    private double totalPrice;
    private List<OrderedProduct> items;
}
