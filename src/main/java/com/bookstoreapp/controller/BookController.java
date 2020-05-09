package com.bookstoreapp.controller;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.UpdateBookDto;
import com.bookstoreapp.modal.Book;
import com.bookstoreapp.response.ResponseDto;
import com.bookstoreapp.service.IBookService;
import com.bookstoreapp.service.Implementation.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin
@RestController
public class BookController {


    @Autowired
    BookService iBookService;


    @PostMapping("/admin/update/book")
    public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody BookDto bookDto,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<ResponseDto>(new ResponseDto(bindingResult.getAllErrors().get(0).getDefaultMessage(),101,"Empty Field"), HttpStatus.BAD_REQUEST);
        }
        String responseMessage= iBookService.addBook(bookDto);
        return new ResponseEntity<ResponseDto>(new ResponseDto("Inserted",200, responseMessage),
                HttpStatus.OK);
    }

    @GetMapping("/admin/books")
    public ResponseDto getAllData(){
        Iterable<Book> allBook = iBookService.getAllBook();
//        return new ResponseEntity<>(new ResponseDto("Request Success",200,allBook),HttpStatus.FOUND);
        ResponseDto response=new ResponseDto("Request Success",200,allBook);
        return response;
    }

    @PostMapping("/admin/update/price")
    public ResponseEntity<ResponseDto> updatePrice(@Valid @RequestBody UpdateBookDto bookDto,
                                                   BindingResult bindingResult) {
        String responseMessage= iBookService.updatePrice(bookDto);
        return new ResponseEntity<ResponseDto>(new ResponseDto("Updated",200, responseMessage),
                HttpStatus.OK);
    }
}
