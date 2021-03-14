package com.intuit.payment.engine;

import com.intuit.payment.dao.model.Payment;
import com.intuit.payment.dao.repository.PaymentRepository;
import com.intuit.payment.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class RiskEngineImpl implements RiskEngine{

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public String savePayment(Payment payment) {
        Random rand = new Random();
        if (rand.nextInt(Constants.ALL_PAYMENT_PERCENTAGE) < Constants.ALLOW_PAYMENT_PERCENTAGE) {
            payment.setId(UUID.randomUUID().toString());
            Payment savedPayment = paymentRepository.save(payment);
            return savedPayment.getId();
        } else {
            return Constants.NOT_APPROVED_STATE;
        }
    }
}
