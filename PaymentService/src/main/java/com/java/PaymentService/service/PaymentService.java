package com.java.PaymentService.service;

import com.java.PaymentService.model.PaymentRequest;
import com.java.PaymentService.model.PaymentResponse;

public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(Long orderId);
}
