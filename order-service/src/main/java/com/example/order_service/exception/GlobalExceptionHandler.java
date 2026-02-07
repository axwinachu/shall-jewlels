package com.example.order_service.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CartISEmpty.class)
    public Response handleCartIsEmpty(Exception ex){
        return new Response(ex.getMessage(),"please add product to cart");
    }
    @ExceptionHandler(OrderNotFound.class)
    public Response handleOrderNotFFound(Exception ex){
        return new Response(ex.getMessage(),"no previous orders found");
    }
    @ExceptionHandler(Exception.class)
    public Response handelGlobalException(Exception ex){
        return new Response(ex.getMessage(),"internal server error");
    }

}
