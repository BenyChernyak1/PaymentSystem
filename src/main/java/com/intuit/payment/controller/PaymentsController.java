package com.intuit.payment.controller;

import com.intuit.payment.dao.model.Payment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PaymentsController {

    ResponseEntity<String> createPayment(Payment payment);

    ResponseEntity<List<String>> getPaymentMethods();

    ResponseEntity<List<String>> getPayees();
}
