package com.bookstoreapp.exception;

import com.bookstoreapp.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BookExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BookException.class)
    public ResponseEntity<Response> bookHandler(BookException bookException){
        return new ResponseEntity(bookException.message, HttpStatus.NOT_FOUND);
    }
}
