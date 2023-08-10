package com.hackerrank.stocktrade.exception;

import com.hackerrank.stocktrade.ApplicationConfig.ApiResponse;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ApiResponse> handleDuplicateKeyException(DuplicateKeyException ex){
        return new ResponseEntity<>(new ApiResponse(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoTradeFoundException.class)
    public ResponseEntity<ApiResponse> handleNoTradeFoundExceptionException(NoTradeFoundException ex){
        return new ResponseEntity<>(new ApiResponse(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleInternalServerErrorException(Exception ex){
        return new ResponseEntity<>(new ApiResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
