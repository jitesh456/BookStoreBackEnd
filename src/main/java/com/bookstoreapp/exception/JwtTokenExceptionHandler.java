package com.bookstoreapp.exception;

import com.bookstoreapp.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class JwtTokenExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Response> bookHandler(JwtTokenException jwtTokenException){
        return new ResponseEntity<Response>(new Response(jwtTokenException.message,
                101, jwtTokenException.exceptionType), HttpStatus.OK);
    }
}
