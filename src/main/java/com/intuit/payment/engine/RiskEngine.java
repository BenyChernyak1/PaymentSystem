package com.intuit.payment.engine;

import com.intuit.payment.dao.model.Payment;

public interface RiskEngine {

    String savePayment(Payment payment);
}
