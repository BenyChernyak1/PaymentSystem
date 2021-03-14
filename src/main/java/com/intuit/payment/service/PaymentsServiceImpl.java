package com.intuit.payment.service;

import com.intuit.payment.dao.model.Payment;
import com.intuit.payment.exception.PaymentException;
import com.intuit.payment.util.Constants;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentsServiceImpl implements PaymentsService{

    @Value("${service.rabbitmq.exchange.name}")
    private String topicExchangeName;

    @Value("${service.rabbitmq.routing.key}")
    private String paymentRoutingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public String createPayment(Payment payment) throws Exception {
        try {
            String savedPaymentId = (String)rabbitTemplate.convertSendAndReceive(topicExchangeName, paymentRoutingKey, payment);
            if (savedPaymentId != null && savedPaymentId.equalsIgnoreCase(Constants.NOT_APPROVED_STATE)) {
                throw new PaymentException("The payment transaction is not approved");
            }
            return savedPaymentId;
        } catch (AmqpException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> getPaymentMethods() throws Exception {
        return null;
    }

    @Override
    public List<String> getPayees() throws Exception {
        return null;
    }
}
