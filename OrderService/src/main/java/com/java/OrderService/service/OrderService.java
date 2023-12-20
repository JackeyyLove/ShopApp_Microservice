package com.java.OrderService.service;

import com.java.OrderService.model.OrderRequest;

public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);
}
