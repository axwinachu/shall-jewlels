package com.example.product_service.service;

import com.example.product_service.model.Product;
import com.example.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("product not found"));
    }
    public List<Product> getListOfProductByIds(List<Long> productIds){
        return productRepository.findAllById(productIds);
    }

    public Boolean getProductIdIsExist(Long id) {
        return productRepository.existsByProductId(id);
    }
}
