package com.bookstoreapp.exception;

public class JwtTokenException extends RuntimeException {

    public enum ExceptionType{
         TOKEN_EXPIRED;
    }
    public ExceptionType exceptionType;
    public String message;

    public JwtTokenException(String message, ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
        this.message = message;
    }
}
