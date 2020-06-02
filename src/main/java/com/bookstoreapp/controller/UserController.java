package com.bookstoreapp.controller;

import com.bookstoreapp.dto.UserLoginDto;
import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@CrossOrigin
@RestController

public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value ="/user")
    public ResponseEntity<Response> addUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),
                    101,"Empty Field"), HttpStatus.BAD_REQUEST);
        }
        boolean responseMessage= userService.addUser(userRegistrationDto);
        return new ResponseEntity<Response>(new Response("User added Sucessfully",200, responseMessage),
                HttpStatus.OK);
    }

    @PostMapping(value ="/login")
    public ResponseEntity<Response> loginUser(@Valid @RequestBody UserLoginDto userLoginDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),
                    101,"Empty Field"), HttpStatus.BAD_REQUEST);
        }
        String token = userService.loginUser(userLoginDto);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("token",token);
        return new ResponseEntity<Response>(new Response("User Login Successfully",200, ""), httpHeaders,HttpStatus.OK);
    }

}