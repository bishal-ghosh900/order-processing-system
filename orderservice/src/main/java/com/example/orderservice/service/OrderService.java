package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    public Order placeOrder(Order order) {
        // the order is not saved to the database

        // generating a random id for the order
        String randomId = UUID.randomUUID().toString();
        order.setOrderId(randomId);

        double totalPrice = twoPrecision(order.getItems().stream()
                .mapToDouble(op -> op.getPrice() * op.getQuantity())
                .reduce(Double::sum)
                .orElse(0));

        // set the total price with two decimal places
        order.setTotalPrice(totalPrice);

        // sending the order to kafka broker
        sendToKafka(order);

        return order;
    }

    private void sendToKafka(Order order) {
        String topic = "order-placed";
        kafkaTemplate.send(topic, order.getOrderId(), order);
    }

    private double twoPrecision(double number) {
        int place = 2;
        double precision = Math.pow(10, place);
        number = Math.round(number * precision);
        return number / precision;
    }
}
