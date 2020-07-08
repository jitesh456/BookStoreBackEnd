package com.bookstoreapp.exception;


public class UserException extends RuntimeException {
    public enum ExceptionType{
        USER_ALREADY_EXIST, INVALID_EMAIL_ID,INVALID_PASSWORD, USER_NOT_FOUND, USER_HAS_NOT_ACTIVATED_ACCOUNT, PLEASE_LOGIN_TO_GIVE_FEEDBACK, YOU_HAD_SUBMITTED_FEEDBACK_PREVIOUSLY,FEEDBACK_NOT_FOUND ;
    }
    public ExceptionType exceptionType;
    public String message;

    public UserException(String message, ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
        this.message = message;
    }
}
