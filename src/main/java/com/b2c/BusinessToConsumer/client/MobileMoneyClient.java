package com.b2c.BusinessToConsumer.client;

import com.b2c.BusinessToConsumer.dto.PaymentRequestDTO;
import com.b2c.BusinessToConsumer.dto.PaymentResponseDTO;

public interface MobileMoneyClient {
    PaymentResponseDTO sendB2CPayment(PaymentRequestDTO request);
}
