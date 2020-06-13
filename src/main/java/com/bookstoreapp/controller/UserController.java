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
import javax.mail.MessagingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@CrossOrigin(origins = "*", exposedHeaders = "Token")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value ="/user")
    public ResponseEntity<Response> addUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto, BindingResult bindingResult,
                                            HttpServletRequest servletRequest) throws MessagingException {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),
                    101,"Empty Field"), HttpStatus.BAD_REQUEST);
        }
        Response responseMessage= userService.addUser(userRegistrationDto,servletRequest);
        return new ResponseEntity<>(responseMessage,
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



    @GetMapping(value = "/verify")
    public ResponseEntity<Response> verifyEmail(@RequestParam("token") String token){
        Response response=userService.verifyEmail(token);
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    @GetMapping(value = "/forget")
    public ResponseEntity<Response> forgetPassword(@RequestParam("email") String email,HttpServletRequest servletRequest) throws MessagingException {
        Response response= userService.forgetPassword(email,servletRequest);
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    @PutMapping(value = "/reset/password")
    public ResponseEntity<Response> resetPassword(@RequestHeader String token, @RequestParam("password") String password ) {
        Response response = userService.resetPassword(token,password);
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }


}