package com.bookstoreapp.controller;

import com.bookstoreapp.dto.NotificationDto;
import com.bookstoreapp.dto.UpdateCartDto;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class CartController {

    @Autowired
    BookService bookService;

    @PutMapping("/book")
    public ResponseEntity<Response> editBook(@Valid @RequestBody UpdateCartDto updateCartDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),
                    101,"Empty Field"), HttpStatus.BAD_REQUEST);
        }
        String responseMessage= bookService.updateQuantity(updateCartDto);
        return new ResponseEntity<Response>(new Response("Book Quantity Updated",200, responseMessage),
                HttpStatus.OK);
    }
    
    @PostMapping("/mail")
    public Response sendMail(@RequestBody NotificationDto notificationDto){
        String mailConfirmation = bookService.sendMail(notificationDto);
        Response response=new Response("Mail Sent Successfully",200,mailConfirmation);
        return response;
    }
}
