package com.bookstoreapp.controller;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.UpdateBookDto;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.response.ResponseDto;
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
    public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody BookDto bookDto,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<ResponseDto>(new ResponseDto(bindingResult.getAllErrors().get(0).getDefaultMessage(),101,"Empty Field"), HttpStatus.BAD_REQUEST);
        }
        String responseMessage= iBookService.addBook(bookDto);
        return new ResponseEntity<ResponseDto>(new ResponseDto("Inserted",200, responseMessage),
                HttpStatus.OK);
    }


    @PutMapping("/book")
    public ResponseEntity<ResponseDto> editBook(@Valid @RequestBody UpdateBookDto bookDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<ResponseDto>(new ResponseDto(bindingResult.getAllErrors().get(0).getDefaultMessage(),
                    101,"Empty Field"), HttpStatus.BAD_REQUEST);
        }
        String responseMessage= iBookService.updatePrice(bookDto);
        return new ResponseEntity<ResponseDto>(new ResponseDto("Updated",200, responseMessage),
                HttpStatus.OK);
    }
}
