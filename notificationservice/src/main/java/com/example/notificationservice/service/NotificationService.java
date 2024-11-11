package com.example.notificationservice.service;

import com.example.notificationservice.model.Order;
import com.mailjet.client.errors.MailjetException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final MailjetService mailjetService;
    private final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @KafkaListener(topics = "order-placed", groupId = "order-placed-consumers")
    public void consume(Order order) throws MailjetException {
        mailjetService.sendEmail(order.getEmail(), order.getOrderId());
        logger.info("Order received");
        logger.info("order id: "+ order.getOrderId());
        logger.info("email: "+ order.getEmail());
    }

}
