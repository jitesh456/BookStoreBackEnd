package com.bookstoreapp.exception;

import com.bookstoreapp.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookException.class)
    public ResponseEntity<Response> globalHandler(BookException bookException){
        return new ResponseEntity(new Response(bookException.message,
                101,bookException.exceptionType),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JwtTokenException.class)
    public ResponseEntity<Response> globalHandler(JwtTokenException jwtTokenException){
        return new ResponseEntity<Response>(new Response(jwtTokenException.message,
                101, jwtTokenException.exceptionType), HttpStatus.OK);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Response> globalHandler(UserException userException) {
        return new ResponseEntity<Response>(new Response(userException.message,
                101, userException.exceptionType), HttpStatus.OK);
    }
}
