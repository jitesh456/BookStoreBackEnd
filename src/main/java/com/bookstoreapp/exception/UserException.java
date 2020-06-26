package com.bookstoreapp.exception;


public class UserException extends RuntimeException {
    public enum ExceptionType{
        USER_ALREADY_EXIST, INVALID_EMAIL_ID,INVALID_PASSWORD, USER_NOT_FOUND,User_Is_Not_Activated_Account;
    }
    public ExceptionType exceptionType;
    public String message;

    public UserException(String message, ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
        this.message = message;
    }
}
