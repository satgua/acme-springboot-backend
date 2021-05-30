package com.acme.mgmt.aop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.acme.mgmt.model.ApiErrorResponse;
 
@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler 
{
 
    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
    	ApiErrorResponse error = new ApiErrorResponse(BigDecimal.valueOf(404), "API token  Not Found");
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
 
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	ApiErrorResponse error = new ApiErrorResponse(BigDecimal.valueOf(400), "Invalid request parameters");
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}