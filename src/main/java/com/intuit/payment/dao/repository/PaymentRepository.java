package com.intuit.payment.dao.repository;

import com.intuit.payment.dao.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, String> {

    @Query("SELECT DISTINCT p.paymentMethodId FROM PAYMENT p")
    List<String> findPaymentMethodIds();

    @Query("SELECT DISTINCT p.payeeId FROM PAYMENT p")
    List<String> findPayeeIds();
}
