package com.bookstoreapp.exception;

import com.bookstoreapp.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BookExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Response> bookHandler(BookException bookException){
        return new ResponseEntity(new Response(bookException.message,
                101,bookException.exceptionType),
                HttpStatus.NOT_FOUND);
    }
}
