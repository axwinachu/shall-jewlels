package com.example.product_service.facade;

import com.example.product_service.dto.ProductResponseDto;
import com.example.product_service.mapper.Mapper;
import com.example.product_service.model.Product;
import com.example.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductFacade {
    private final ProductService productService;

    private final Mapper mapper;
    public List<ProductResponseDto> getAllProduct() {
        return productService.getAllProduct().stream().map(mapper::productToProductResponseDto).toList();
    }
    public ProductResponseDto getProductById(Long id) {
        return mapper.productToProductResponseDto(productService.getProductById(id));
    }

    public List<ProductResponseDto> getListOfProductsByIds(List<Long> ids) {
        return productService.getListOfProductByIds(ids).stream().map(mapper::productToProductResponseDto).toList();
    }

    public Boolean getProductIdIsExist(Long id) {
        return productService.getProductIdIsExist(id);
    }
}
