package com.bookstoreapp.response;

import org.springframework.http.HttpStatus;

public class ResponseDto {

     public String message;
    int statusCode;
    Object body;

    public ResponseDto(String message, int statusCode, Object body) {
        this.message = message;
        this.statusCode = statusCode;
        this.body = body;
    }
}
