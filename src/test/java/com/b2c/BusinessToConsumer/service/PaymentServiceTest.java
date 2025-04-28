package com.b2c.BusinessToConsumer.service;

import com.b2c.BusinessToConsumer.client.MobileMoneyClient;
import com.b2c.BusinessToConsumer.client.SmsGatewayClient;
import com.b2c.BusinessToConsumer.dto.PaymentRequestDTO;
import com.b2c.BusinessToConsumer.dto.PaymentResponseDTO;
import com.b2c.BusinessToConsumer.repository.PaymentTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    private PaymentService paymentService;
    private MobileMoneyClient mobileMoneyClient;
    private SmsGatewayClient smsGatewayClient;

    @BeforeEach
    void setUp() {
        mobileMoneyClient = mock(MobileMoneyClient.class);
        smsGatewayClient = mock(SmsGatewayClient.class);


        paymentService = new PaymentService(mobileMoneyClient, smsGatewayClient, mock(PaymentTransactionRepository.class));
    }

    @Test
    void shouldSuccessfullyProcessPayment() {
        // given
        PaymentRequestDTO request = new PaymentRequestDTO("254700000001", new BigDecimal("100.0"), "test", "test-ref");

        when(mobileMoneyClient.sendB2CPayment(any())).thenReturn(new PaymentResponseDTO("tx123", "SUCCESS", "Payment completed"));
        doNothing().when(smsGatewayClient).sendSms(any(), any());

        // when
        PaymentResponseDTO response = paymentService.processPayment(request);

        // then
        assertThat(response.getStatus()).isEqualTo("SUCCESS");
        verify(mobileMoneyClient, times(1)).sendB2CPayment(any());
        verify(smsGatewayClient, times(1)).sendSms(any(), any());
    }

    @Test
    void shouldFailPaymentWhenMobileMoneyFails() {

        PaymentRequestDTO request = new PaymentRequestDTO("254700000002", new BigDecimal("200.0"), "test-failure", "test-ref");

        when(mobileMoneyClient.sendB2CPayment(any())).thenReturn(new PaymentResponseDTO("tx124", "FAILED", "Payment failed"));
        doNothing().when(smsGatewayClient).sendSms(any(), any());

        PaymentResponseDTO response = paymentService.processPayment(request);


        assertThat(response.getStatus()).isEqualTo("FAILED");
        verify(mobileMoneyClient, times(1)).sendB2CPayment(any());
        verify(smsGatewayClient, times(1)).sendSms(any(), any());
    }
}