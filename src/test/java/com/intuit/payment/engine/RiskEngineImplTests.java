package com.intuit.payment.engine;

import com.intuit.payment.dao.model.Payment;
import com.intuit.payment.dao.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RiskEngineImplTests {

    private String id;

    @InjectMocks
    private RiskEngineImpl riskEngine;

    @Mock
    private PaymentRepository paymentRepository;

    @BeforeEach
    void init() {
        id = UUID.randomUUID().toString();
    }

    @Test
    void savePayment() throws Exception {
        Payment payment = new Payment();
        payment.setId(id);
        payment.setPaymentMethodId(UUID.randomUUID().toString());
        payment.setPayeeId(UUID.randomUUID().toString());
        payment.setUserId(UUID.randomUUID().toString());
        Random rand = new Random();
        payment.setAmount(10d + (1000000d - 10d) * rand.nextDouble());
        payment.setCurrency("USD");

        ArgumentCaptor<Payment> paymentArgumentCaptor = ArgumentCaptor.forClass(Payment.class);
        when(paymentRepository.save(any(Payment.class))).thenAnswer((Answer<Payment>) invocation -> {
            Payment payment1 = new Payment();
            Payment paymentArg = (Payment)invocation.getArguments()[0];
            payment1.setId(paymentArg.getId());

            return payment1;
        });

        riskEngine.savePayment(payment);
        verify(paymentRepository, times(1)).save(paymentArgumentCaptor.capture());

        assertNotNull(paymentArgumentCaptor.getValue().getId());
    }
}
