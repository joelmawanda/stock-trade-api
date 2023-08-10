package com.hackerrank.stocktrade.exception;

public class NoTradeFoundException extends RuntimeException{
    public NoTradeFoundException(String message) {
        super(message);
    }
}
