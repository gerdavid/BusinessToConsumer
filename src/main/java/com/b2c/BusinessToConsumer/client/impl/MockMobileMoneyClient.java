package com.b2c.BusinessToConsumer.client.impl;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import com.b2c.BusinessToConsumer.dto.PaymentRequestDTO;
import com.b2c.BusinessToConsumer.dto.PaymentResponseDTO;
import com.b2c.BusinessToConsumer.client.MobileMoneyClient;

@Component
public class MockMobileMoneyClient implements MobileMoneyClient {

    private static final Logger log = LoggerFactory.getLogger(MockMobileMoneyClient.class);

    @Override
    public PaymentResponseDTO sendB2CPayment(PaymentRequestDTO request) {
        log.info("Mock sending B2C payment to provider: {}", request.getProvider());

        boolean success = Math.random() > 0.2;

        String transactionId = UUID.randomUUID().toString();
        String status = success ? "SUCCESS" : "FAILED";
        String message = success ? "Payment completed successfully" : "Payment failed due to provider error";

        log.info("Mock Mobile Money Transaction ID: {}, Status: {}", transactionId, status);

        return new PaymentResponseDTO(transactionId, status, message);
    }
}