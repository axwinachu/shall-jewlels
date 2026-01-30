package com.example.product_service.model;

import com.example.product_service.model.Enums.Availability;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "products")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private String shortDescription;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private BigDecimal discountPrice;
    @Column(nullable = false)
    private Long taxPercentage;
    @Column(nullable = false)
    private Long stockQuantity;
    private String mainImageUrl;
    private List<String> imageUrl;
    @Enumerated(EnumType.STRING)
    private Availability availability;
    private Integer reviewCount;
    private Long review;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
