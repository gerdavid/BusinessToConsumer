package com.b2c.BusinessToConsumer.repository;

import com.b2c.BusinessToConsumer.entity.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {
}
