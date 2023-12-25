package com.java.OrderService.service.impl;

import com.java.OrderService.entity.Order;
import com.java.OrderService.external.client.PaymentService;
import com.java.OrderService.external.client.ProductService;
import com.java.OrderService.external.request.PaymentRequest;
import com.java.OrderService.model.OrderRequest;
import com.java.OrderService.repository.OrderRepository;
import com.java.OrderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final PaymentService paymentService;
    @Override
    public Long placeOrder(OrderRequest orderRequest) {
        // Order Entity -> Save the data with status Order created;
        // Product service -> Reduce the quantity;
        // Payment service -> payments -> Success -> COMPLETED / CANCELLED
        log.info("Placing order request: {}", orderRequest);
        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();
        order = orderRepository.save(order);
        log.info("Calling payment service to complete the payment");
        PaymentRequest paymentRequest = PaymentRequest.builder()
                        .orderId(order.getId())
                        .paymentMode(orderRequest.getPaymentMode())
                        .amount(orderRequest.getTotalAmount())
                        .build();
        String orderStatus = null;
        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment done successfully");
            orderStatus = "PLACED";
        } catch (Exception e) {
            log.error("Error occurred in payment");
            orderStatus = "PAYMENT_FAILED";
        }
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        log.info("Order places successfully with order id: {}", order.getId());
        return order.getId();
    }
}
