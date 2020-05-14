package com.bookstoreapp.controller;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.UpdateBookDto;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin
@RequestMapping("/admin")
@RestController
public class AdminController {


    @Autowired
    BookService iBookService;


    @PostMapping("/book")
    public ResponseEntity<Response> addBook(@Valid @RequestBody BookDto bookDto,
                                            BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),101,"Empty Field"), HttpStatus.BAD_REQUEST);
        }
        String responseMessage= iBookService.addBook(bookDto);
        return new ResponseEntity<Response>(new Response("Book Added Successfully",200, responseMessage),
                HttpStatus.OK);
    }


    @PutMapping("/book")
    public ResponseEntity<Response> editBook(@Valid @RequestBody UpdateBookDto bookDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),
                    101,"Empty Field"), HttpStatus.BAD_REQUEST);
        }
        String responseMessage= iBookService.updatePrice(bookDto);
        return new ResponseEntity<Response>(new Response("Book is Updated",200, responseMessage),
                HttpStatus.OK);
    }
}
