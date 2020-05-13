package com.bookstoreapp.controller;

import com.bookstoreapp.model.Book;
import com.bookstoreapp.response.ResponseDto;
import com.bookstoreapp.service.Implementation.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class BookController {


    @Autowired
    BookService iBookService;

    @GetMapping("/api/v1/books")
    public ResponseDto getAllData(){
        Iterable<Book> allBook = iBookService.getAllBook();
        ResponseDto response=new ResponseDto("Request Success",200,allBook);
        return response;
    }

    @GetMapping("/api/v2/books")
    public ResponseDto getSortedBook(@RequestParam ("field")String field){
        Iterable<Book> sortedBook = iBookService.getSortedBook(field);
        ResponseDto response=new ResponseDto("Request Success",200,sortedBook);
        return response;
    }
}
