package com.example.cart_service.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CartNotFound.class)
    public Response handleCartNotFound(Exception ex){
        return new Response(ex.getMessage(),"in the user id cart not found");
    }
    @ExceptionHandler(InvalidQuantity.class)
    public Response handleInvalidQuantity(Exception ex){
        return new Response(ex.getMessage(),"Please choose the positive value");
    }
    @ExceptionHandler(ProductNotFound.class)
    public Response handleProductNotFound(Exception ex){
        return new Response(ex.getMessage(),"product is not available in the id");
    }
    @ExceptionHandler(Exception.class)
    public Response handleGlobalException(Exception ex){
        return new Response(ex.getMessage(),"internal server error");
    }

}
