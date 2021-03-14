package com.intuit.payment.service;

import com.intuit.payment.dao.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PaymentsServiceImplTests {

    private String id;

    @InjectMocks
    private PaymentsServiceImpl paymentsService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private RabbitTemplate rt;

    @BeforeEach
    void init() {
        id = UUID.randomUUID().toString();
    }

    @Test
    void savePayment() throws Exception {
//        RabbitTemplate rt = Mockito.mock(RabbitTemplate.class);
//        Payment payment = new Payment();
//        payment.setId(id);
//        payment.setPaymentMethodId(UUID.randomUUID().toString());
//        payment.setPayeeId(UUID.randomUUID().toString());
//        payment.setUserId(UUID.randomUUID().toString());
//        Random rand = new Random();
//        payment.setAmount(10d + (1000000d - 10d) * rand.nextDouble());
//        payment.setCurrency("USD");
//
//        ArgumentCaptor<Payment> paymentArgumentCaptor = ArgumentCaptor.forClass(Payment.class);
//        when(paymentRepository.save(Mockito.any())).thenReturn(paymentArgumentCaptor.capture());
//        when(rt.convertSendAndReceive(null, null, payment)).thenReturn(id);
//
//        paymentsService.createPayment(payment);
//        verify(paymentRepository, times(1)).save(paymentArgumentCaptor.capture());
//
//        assertTrue(paymentArgumentCaptor.getValue().getId().equalsIgnoreCase(id));
    }
}
