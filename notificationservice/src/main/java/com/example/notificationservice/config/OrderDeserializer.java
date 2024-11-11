package com.example.notificationservice.config;

import com.example.notificationservice.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class OrderDeserializer implements Deserializer<Order> {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public Order deserialize(String s, byte[] bytes) {
        try {
            return mapper.readValue(bytes, Order.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
