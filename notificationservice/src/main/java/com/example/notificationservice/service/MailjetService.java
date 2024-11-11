package com.example.notificationservice.service;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.transactional.SendContact;
import com.mailjet.client.transactional.SendEmailsRequest;
import com.mailjet.client.transactional.TransactionalEmail;
import com.mailjet.client.transactional.response.SendEmailsResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class MailjetService {

    private final MailjetClient client;
    private final Logger logger = LoggerFactory.getLogger(MailjetService.class);

    @Value("${sender.email}")
    private String fromEmail;

    public void sendEmail(String toEmail, String orderId) throws MailjetException {
        TransactionalEmail message = TransactionalEmail.builder()
                .to(new SendContact(toEmail))
                .from(new SendContact(fromEmail))
                .subject("Order placed")
                .htmlPart("<h4>Your order is placed successfully. The order id is " + orderId + ".</h4>")
                .build();
        SendEmailsRequest request = SendEmailsRequest.builder()
                .message(message)
                .build();
        SendEmailsResponse response = request.sendWith(client);
        logger.info(Arrays.toString(response.getMessages()));
    }
}
