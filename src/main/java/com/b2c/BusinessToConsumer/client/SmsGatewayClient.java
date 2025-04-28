package com.b2c.BusinessToConsumer.client;

public interface SmsGatewayClient {
    void sendSms(String phoneNumber, String message);
}
