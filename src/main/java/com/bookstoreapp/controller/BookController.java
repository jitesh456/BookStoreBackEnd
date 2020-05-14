package com.bookstoreapp.controller;

import com.bookstoreapp.model.Book;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class BookController {


    @Autowired
    BookService iBookService;

    @GetMapping("/books")
    public Response getAllData(){
        Iterable<Book> allBook = iBookService.getAllBook();
        Response response=new Response("Request Success",200,allBook);
        return response;
    }

    @GetMapping("/books/field")
    public Response getSortedBook(@RequestParam ("field")String field){
        if(field.isEmpty())
        {
            return  new Response("Field cant be null for sorting",400,null);
        }
        Iterable<Book> sortedBook = iBookService.getSortedBook(field);
        Response response=new Response("Request Success",200,sortedBook);
        return response;
    }
}
