package com.bookstoreapp.exception;


public class UserException extends RuntimeException {
    public enum ExceptionType{
        USER_ALREADY_EXIST, INVALID_EMAIL_ID;
    }
    public UserException.ExceptionType exceptionType;
    public String message;

    public UserException(String message, UserException.ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
        this.message = message;
    }
}