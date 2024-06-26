package com.bookstoreapp.controller;

import com.bookstoreapp.dto.FeedbackDto;
import com.bookstoreapp.dto.UserDetailDto;
import com.bookstoreapp.model.Feedback;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.CustomerService;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/customerdetail")
    public ResponseEntity<Response> userDetails(@Valid @RequestBody UserDetailDto userDetailDto,
                                                BindingResult bindingResult,
                                                @RequestHeader String token) {

        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),
                    101,"Empty Field"), HttpStatus.BAD_REQUEST);
        }
        Response response=customerService.userDetail(userDetailDto,token);
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    @GetMapping(value = "/customerdetail")
    public ResponseEntity<Response> getUserDetails(@RequestHeader String token) {

        Response response=customerService.getUserDetail(token);
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> addUserFeedback(@RequestBody FeedbackDto feedbackDto,
                                                    BindingResult bindingResult,
                                                    @RequestHeader String token){
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),
                    101,"Empty Field"), HttpStatus.BAD_REQUEST);
        }
        Response response=customerService.addFeedback(token,feedbackDto);
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    @GetMapping(value= "/feedback")
    public ResponseEntity<Response> getBookFeedBack(@RequestParam("isbn") String isbn){
        Response response = customerService.getAllFeedback(isbn);
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    @GetMapping(value = "/customer/feedback")
    public ResponseEntity<Response> getCustomerFeedback(@RequestParam("id") int id,@RequestHeader String token){
        Response customerFeedback = customerService.getUserFeedback(id,token);
        return new ResponseEntity<>(customerFeedback,HttpStatus.OK);
    }
}
