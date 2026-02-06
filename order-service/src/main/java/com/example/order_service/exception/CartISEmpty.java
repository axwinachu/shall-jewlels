package com.example.order_service.exception;

public class CartISEmpty extends RuntimeException {
    public CartISEmpty(String message) {
        super(message);
    }
}
