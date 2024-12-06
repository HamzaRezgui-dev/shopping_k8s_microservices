package com.hamza.microservices.product.service;


import com.hamza.microservices.product.dto.ProductRequest;
import com.hamza.microservices.product.dto.ProductResponse;
import com.hamza.microservices.product.mapper.ProductMapper;
import com.hamza.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    public ProductResponse createProduct(ProductRequest productRequest) {
        var product = productRepository.save(productMapper.toProduct(productRequest));
        return productMapper.toProductResponse(product);
    }

    public ProductResponse getProduct(String id) {
        var product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toProductResponse(product);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
