package com.b2c.BusinessToConsumer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipientPhoneNumber;
    private BigDecimal amount;
    private String currency;
    private String provider;
    private String transactionId;
    private String status;
    private String message;
}