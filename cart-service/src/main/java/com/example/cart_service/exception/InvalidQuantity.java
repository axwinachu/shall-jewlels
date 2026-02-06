package com.example.cart_service.exception;

public class InvalidQuantity extends RuntimeException {
    public InvalidQuantity(String message) {
        super(message);
    }
}
