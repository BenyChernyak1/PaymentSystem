package com.intuit.payment.service;

import com.intuit.payment.dao.model.Payment;

import java.util.List;

public interface PaymentsService {

    String createPayment(Payment payment) throws Exception;

    List<String> getPaymentMethods() throws Exception;

    List<String> getPayees() throws Exception;
}
