package com.bookstoreapp.controller;

import com.bookstoreapp.dto.UserDetailDto;
import com.bookstoreapp.dto.UserLoginDto;
import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@CrossOrigin(origins = "*", exposedHeaders = "Token")
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
        Response responseMessage= userService.addUser(userRegistrationDto);
        return new ResponseEntity<Response>(responseMessage      ,
                HttpStatus.OK);
    }

    @PostMapping(value ="/login")
    public ResponseEntity<Response> loginUser(@Valid @RequestBody UserLoginDto userLoginDto, BindingResult bindingResult,HttpServletResponse httpHeaders){
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),
                    101,"Empty Field"), HttpStatus.BAD_REQUEST);
        }
        String token = userService.loginUser(userLoginDto);

        httpHeaders.setHeader("Token",token);
        return new ResponseEntity<Response>(new Response("User Login Successfully",200, ""),HttpStatus.OK);
    }

    @PostMapping(value = "/userdetail")
    public ResponseEntity<Response> userDetails(@Valid @RequestBody UserDetailDto userDetailDto, BindingResult bindingResult, @RequestHeader String token){
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),
                    101,"Empty Field"), HttpStatus.BAD_REQUEST);
        }
        Response response=userService.userDetail(userDetailDto,token);
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }
}