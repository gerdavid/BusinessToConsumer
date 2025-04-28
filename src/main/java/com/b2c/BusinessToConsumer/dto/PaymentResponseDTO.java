package com.b2c.BusinessToConsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResponseDTO {

    private String transactionId;
    private String status;
    private String message;
}
