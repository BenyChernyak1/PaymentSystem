package com.intuit.payment.controller;

import com.intuit.payment.controller.common.CORSFilter;
import com.intuit.payment.controller.common.Converter;
import com.intuit.payment.dao.model.Payment;
import com.intuit.payment.service.PaymentsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PaymentsControllerImplTest {

    @Mock
    private PaymentsService paymentsService;

    @InjectMocks
    private PaymentsControllerImpl paymentsController;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(paymentsController)
                .addFilters(new CORSFilter())
                .build();
    }

    @Test
    public void testCreatePayment() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(paymentsService.createPayment(any(Payment.class))).thenReturn(any(String.class));

        ResponseEntity<String> responseEntity = paymentsController.createPayment(createPayment());

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testCreatePayment_success() throws Exception {
        ArgumentCaptor<Payment> paymentArgumentCaptor = ArgumentCaptor.forClass(Payment.class);

        when(paymentsService.createPayment(paymentArgumentCaptor.capture())).thenReturn(any(String.class));

        Payment payment = createPayment();

        MockHttpServletRequestBuilder post = post("/intuit/payment")
                .content(Converter.asJsonString(payment))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(post)
                .andExpect(status().isOk())
                .andDo(print());

        Payment value = paymentArgumentCaptor.getValue();
        assertNotNull(value);
    }

    private Payment createPayment() {
        Payment payment = new Payment();
        payment.setPaymentMethodId(UUID.randomUUID().toString());
        payment.setPayeeId(UUID.randomUUID().toString());
        payment.setUserId(UUID.randomUUID().toString());
        Random rand = new Random();
        payment.setAmount(10d + (1000000d - 10d) * rand.nextDouble());
        payment.setCurrency("USD");
        return payment;
    }
}
