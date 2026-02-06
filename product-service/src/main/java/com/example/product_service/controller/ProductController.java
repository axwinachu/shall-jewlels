package com.example.product_service.controller;

import com.example.product_service.dto.ProductResponseDto;
import com.example.product_service.facade.ProductFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductFacade productFacade;
    @GetMapping("/all")
    public List<ProductResponseDto> getAllProduct(){
        return productFacade.getAllProduct();
    }
    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable Long id){
        return productFacade.getProductById(id);
    }
    @PostMapping("/list")
    public List<ProductResponseDto> getListOfProductByIds(@RequestBody List<Long> ids){
        return productFacade.getListOfProductsByIds(ids);
    }
    @GetMapping("/exist/{id}")
    public Boolean productIdIsExist(@PathVariable Long id){
        return productFacade.getProductIdIsExist(id);
    }
}
