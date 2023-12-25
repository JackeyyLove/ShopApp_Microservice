package com.java.OrderService.service;

import com.java.OrderService.model.OrderRequest;
import com.java.OrderService.model.OrderResponse;

public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(Long orderId);
}
