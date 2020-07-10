package com.bookstoreapp.controller;

import com.bookstoreapp.dto.AddToCartDto;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.AdminService;
import com.bookstoreapp.service.Implementation.CartService;
import com.bookstoreapp.util.implementation.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.validation.Valid;

@CrossOrigin
@RestController
public class CartController {


    @Autowired
    AdminService adminService;

    @Autowired
    CartService cartService;

    @Autowired
    SendMail mailSender;

    @PostMapping("/book")
    public ResponseEntity<Response> addToCart(@Valid @RequestBody AddToCartDto addToCartDto,
                                              BindingResult result,
                                              @RequestHeader String token) {
        if(result.hasErrors()) {
            return new  ResponseEntity<>(new Response(result.getAllErrors().get(0).getDefaultMessage(),400,""),
                    HttpStatus.BAD_REQUEST);
        }
        Response response = cartService.addToCart(addToCartDto, token);
        return new  ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/book")
    public ResponseEntity<Response> getCartBooks(@RequestHeader String token) {

        Response response = cartService.getCartBook(token);
        return new  ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/cart")
    public  ResponseEntity<Response> updateCart(@RequestHeader String token) throws MessagingException {

        Response response=cartService.updateCart(token);
        return new ResponseEntity<Response> (response,HttpStatus.OK);
    }

    @DeleteMapping(value="/book/{id}")
    public ResponseEntity<Response> deleteBook(@PathVariable int id,@RequestHeader String token) {

        Response response = cartService.deleteBook(id, token);
        return new  ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping(value="/order/details")
    public ResponseEntity<Response> orderDetails(@RequestHeader String token) {

        Response response = cartService.orderDetails(token);
        return new  ResponseEntity<>(response,HttpStatus.OK);
    }

}
