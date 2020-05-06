package com.bookstoreapp.exception;

public class BookException extends RuntimeException {
    public enum ExceptionType{
            BOOK_ALREADY_EXIST
}
    public ExceptionType exceptionType;
    public String message;

    public BookException(String message, ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
        this.message = message;
    }
}
