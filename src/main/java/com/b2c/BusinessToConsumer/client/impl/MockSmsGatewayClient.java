package com.b2c.BusinessToConsumer.client.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.b2c.BusinessToConsumer.client.SmsGatewayClient;
@Component
@Slf4j
public class MockSmsGatewayClient implements SmsGatewayClient {

    @Override
    public void sendSms(String phoneNumber, String message) {
        log.info("Mock sending SMS to {}: {}", phoneNumber, message);
    }
}
