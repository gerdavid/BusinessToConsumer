package com.b2c.BusinessToConsumer.service;

import com.b2c.BusinessToConsumer.client.MobileMoneyClient;
import com.b2c.BusinessToConsumer.client.SmsGatewayClient;
import com.b2c.BusinessToConsumer.dto.PaymentRequestDTO;
import com.b2c.BusinessToConsumer.dto.PaymentResponseDTO;
import com.b2c.BusinessToConsumer.entity.PaymentTransaction;
import com.b2c.BusinessToConsumer.repository.PaymentTransactionRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final MobileMoneyClient mobileMoneyClient;
    private final SmsGatewayClient smsGatewayClient;
    private final PaymentTransactionRepository paymentTransactionRepository;

    public PaymentResponseDTO processPayment(PaymentRequestDTO requestDTO) {
        log.info("Processing B2C payment for {}", requestDTO.getRecipientPhoneNumber());

        PaymentResponseDTO paymentResponseDTO = mobileMoneyClient.sendB2CPayment(requestDTO);

        PaymentTransaction transaction = new PaymentTransaction();
        transaction.setRecipientPhoneNumber(requestDTO.getRecipientPhoneNumber());
        transaction.setAmount(requestDTO.getAmount());
        transaction.setCurrency(requestDTO.getCurrency());
        transaction.setProvider(requestDTO.getProvider());
        transaction.setTransactionId(paymentResponseDTO.getTransactionId());
        transaction.setStatus(paymentResponseDTO.getStatus());
        transaction.setMessage(paymentResponseDTO.getMessage());

        paymentTransactionRepository.save(transaction);

        String notificationMessage = String.format(
                "Transaction %s. Amount: %s %s. Ref: %s",
                paymentResponseDTO.getStatus(),
                requestDTO.getAmount(),
                requestDTO.getCurrency(),
                paymentResponseDTO.getTransactionId()
        );
        smsGatewayClient.sendSms(requestDTO.getRecipientPhoneNumber(), notificationMessage);

        return paymentResponseDTO;
    }
}
