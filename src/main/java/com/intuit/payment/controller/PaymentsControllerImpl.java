package com.intuit.payment.controller;

import com.google.common.base.Preconditions;
import com.intuit.payment.dao.model.Payment;
import com.intuit.payment.exception.PaymentException;
import com.intuit.payment.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("intuit")
public class PaymentsControllerImpl implements PaymentsController{

    @Autowired
    private PaymentsService paymentsService;

    @Override
    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public ResponseEntity<String> createPayment(@RequestBody Payment payment) {
        try {
            Preconditions.checkArgument(payment != null);
            Preconditions.checkArgument(payment.getPaymentMethodId() != null);
            Preconditions.checkArgument(payment.getPayeeId() != null);
            Preconditions.checkArgument(payment.getUserId() != null);
            String paymentId = paymentsService.createPayment(payment);
            return new ResponseEntity<>(paymentId, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (PaymentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(value = "/payment/methods", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getPaymentMethods() {
        try {
            List<String> paymentMethodIds = paymentsService.getPaymentMethods();
            return new ResponseEntity<>(paymentMethodIds, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(value = "/payment/payees", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getPayees() {
        try {
            List<String> payeeIds = paymentsService.getPayees();
            return new ResponseEntity<>(payeeIds, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
