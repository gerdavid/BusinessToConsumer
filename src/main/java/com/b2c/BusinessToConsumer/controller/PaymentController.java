package com.b2c.BusinessToConsumer.controller;

import com.b2c.BusinessToConsumer.dto.PaymentRequestDTO;
import com.b2c.BusinessToConsumer.dto.PaymentResponseDTO;
import com.b2c.BusinessToConsumer.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
//    public PaymentController(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }
    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_payment:write')")
    public PaymentResponseDTO initiatePayment(@RequestBody @Valid PaymentRequestDTO paymentRequestDTO) {
        return paymentService.processPayment(paymentRequestDTO);
    }
}
