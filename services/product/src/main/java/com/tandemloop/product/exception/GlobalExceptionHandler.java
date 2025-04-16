package com.tandemloop.product.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductPurchaseException.class)
    public ResponseEntity<String> handle(ProductPurchaseException exp){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exp.getMsg());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handle(EntityNotFoundException exp){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exp.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exp){
    var errors=new HashMap<String,String>();
    exp.getBindingResult().getAllErrors().forEach(er->{
        var fieldName=((FieldError)er).getField();
        var errorMsg=er.getDefaultMessage();
        errors.put(fieldName,errorMsg);
    });

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }
}
