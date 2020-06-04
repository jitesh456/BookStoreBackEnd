package com.bookstoreapp.controller;

import com.bookstoreapp.dto.AddToCartDto;
import com.bookstoreapp.dto.NotificationDto;
import com.bookstoreapp.dto.UpdateCartDto;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.BookService;
import com.bookstoreapp.service.Implementation.CartService;
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
    BookService bookService;

    @Autowired
    CartService cartService;

    @PutMapping("/book")
    public ResponseEntity<Response> editBook(@Valid @RequestBody UpdateCartDto updateCartDto, BindingResult bindingResult,@RequestHeader String token) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),
                    101,"Empty Field"), HttpStatus.BAD_REQUEST);
        }
        String responseMessage= bookService.updateQuantity(updateCartDto);
        return new ResponseEntity<Response>(new Response("Book Quantity Updated",200, responseMessage),
                HttpStatus.OK);
    }
    
    @PostMapping("/mail")
    public Response sendMail(@RequestBody NotificationDto notificationDto) throws MessagingException {
        String mailConfirmation = bookService.sendMail(notificationDto);
        Response response=new Response("Mail Sent Successfully",200,mailConfirmation);
        return response;
    }

    @PostMapping("/book")
    public ResponseEntity<Response> addToCart(@RequestBody AddToCartDto addToCartDto,@RequestHeader String token){
        Response response = cartService.addToCart(addToCartDto, token);
        return new  ResponseEntity<>(response,HttpStatus.CREATED);
    }
}
