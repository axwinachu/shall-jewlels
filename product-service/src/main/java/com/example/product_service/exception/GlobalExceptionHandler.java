package com.example.product_service.exception;

import com.example.product_service.exception.responce.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFound.class)
    public Response handleProductNotFoundException(Exception ex){
        return new Response("404",ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception ex){
        return new Response("500","exception triggered");
    }
}
