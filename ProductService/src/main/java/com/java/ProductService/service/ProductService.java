package com.java.ProductService.service;

import com.java.ProductService.model.ProductRequest;
import com.java.ProductService.model.ProductResponse;

public interface ProductService {
    Long addProduct(ProductRequest productRequest);
    ProductResponse getProductById(Long productId);

    void reduceQuantity(Long productId, Long quantity);
}
