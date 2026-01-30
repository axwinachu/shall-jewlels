package com.example.product_service.mapper;

import com.example.product_service.dto.ProductResponseDto;
import com.example.product_service.model.Enums.Availability;
import com.example.product_service.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Mapper {
    private Long productId;
    private String productName;
    private String shortDescription;
    private String description;
    private String brand;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private Long taxPercentage;
    private Long stockQuantity;
    private String mainImageUrl;
    private List<String> imageUrl;
    private Availability availability;
    private Integer reviewCount;
    private Long review;
    public ProductResponseDto productToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .shortDescription(product.getShortDescription())
                .description(product.getDescription())
                .brand(product.getBrand())
                .price(product.getPrice())
                .discountPrice(product.getDiscountPrice())
                .taxPercentage(product.getTaxPercentage())
                .stockQuantity(product.getStockQuantity())
                .mainImageUrl(product.getMainImageUrl())
                .imageUrl(product.getImageUrl())
                .availability(product.getAvailability())
                .reviewCount(product.getReviewCount())
                .review(product.getReview())
                .build();
    }
}
