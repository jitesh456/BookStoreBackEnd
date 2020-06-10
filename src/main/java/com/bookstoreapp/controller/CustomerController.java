package com.bookstoreapp.controller;

import com.bookstoreapp.dto.UserDetailDto;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/userdetail")
    public ResponseEntity<Response> userDetails(@Valid @RequestBody UserDetailDto userDetailDto, BindingResult bindingResult, @RequestHeader String token){
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),
                    101,"Empty Field"), HttpStatus.BAD_REQUEST);
        }
        Response response=customerService.userDetail(userDetailDto,token);
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    @GetMapping(value = "/fetchdetail")
    public ResponseEntity<Response> getUserDetails(@RequestHeader String token){
        Response response=customerService.getUserDetail(token);
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

}
