package com.bookstoreapp.response;

import org.springframework.http.HttpStatus;

public class ResponseDto {

     public String message;
    int statusCode;
    Object body;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public ResponseDto(String message, int statusCode, Object body) {
        this.message = message;
        this.statusCode = statusCode;
        this.body = body;
    }
}
