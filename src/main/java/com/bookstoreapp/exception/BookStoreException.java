package com.bookstoreapp.exception;

public class BookStoreException extends RuntimeException {
    String message;

    public BookStoreException(String message) {
        this.message = message;
    }
}
