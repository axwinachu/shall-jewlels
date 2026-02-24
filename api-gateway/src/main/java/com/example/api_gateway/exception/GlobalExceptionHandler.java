package com.example.api_gateway.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.awt.print.Pageable;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidToken.class)
    public Response handleInvalidToke(Exception ex) {
        return new Response(ex.getMessage(), "invalid credential");
    }
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception ex){
        return new Response(ex.getMessage(),"internal server error");
    }

}
