package com.java.OrderService.service.impl;

import com.java.OrderService.entity.Order;
import com.java.OrderService.model.OrderRequest;
import com.java.OrderService.repository.OrderRepository;
import com.java.OrderService.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Long placeOrder(OrderRequest orderRequest) {
        // Order Entity -> Save the data with status Order created;
        // Product service -> Reduce the quantity;
        // Payment service -> payments -> Success -> COMPLETED / CANCELLED
        log.info("Placing order request: {}", orderRequest);
        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();
        order = orderRepository.save(order);
        log.info("Order places successfully with order id: {}", order.getId());
        return order.getId();
    }
}
