package com.bookstoreapp.controller;

import com.bookstoreapp.dto.BookStoreDto;
import com.bookstoreapp.response.ResponseDto;
import com.bookstoreapp.service.IBookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BookStoreController {


    @Autowired
    IBookStoreService iBookStoreService;


    @PostMapping("/add")
    public ResponseEntity<ResponseDto> register(@Valid @RequestBody BookStoreDto bookStoreDto,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<ResponseDto>(new ResponseDto(bindingResult.getAllErrors().get(0).getDefaultMessage(),101,"Empty Field"), HttpStatus.BAD_REQUEST);
        }
        String responseMessage= iBookStoreService.addBook(bookStoreDto);
        return new ResponseEntity<ResponseDto>(new ResponseDto("Inserted",1, responseMessage),
                HttpStatus.OK);
    }
}
