package com.java.ProductService.service.serviceimpl;

import com.java.ProductService.entity.Product;
import com.java.ProductService.exception.ProductServiceCustomException;
import com.java.ProductService.model.ProductRequest;
import com.java.ProductService.model.ProductResponse;
import com.java.ProductService.repository.ProductRepository;
import com.java.ProductService.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    private final ModelMapper modelMapper;
    @Override
    public Long addProduct(ProductRequest productRequest) {
        log.info("Adding product...");
        Product product = Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        log.info("Get the product for productId: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException("Cannot find product with id: " + productId, "PRODUCT_NOT_FOUND"));
        ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
        return productResponse;
    }
}
