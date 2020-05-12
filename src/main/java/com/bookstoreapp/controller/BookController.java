package com.bookstoreapp.controller;

import com.bookstoreapp.model.Book;
import com.bookstoreapp.response.ResponseDto;
import com.bookstoreapp.service.Implementation.BookService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class BookController {


    @Autowired
    BookService iBookService;

    @GetMapping("/books")
    public ResponseDto getAllData(){
        Iterable<Book> allBook = iBookService.getAllBook();
        ResponseDto response=new ResponseDto("Request Success",200,allBook);
        return response;
    }
}
