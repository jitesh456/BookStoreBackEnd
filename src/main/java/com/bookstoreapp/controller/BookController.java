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
    BookService bookService;

    @GetMapping("/books")
    public Response getAllData() {
        Iterable<Book> allBook = bookService.getAllBook();
        Response response=new Response("Fetched Books",200,allBook);
        return response;
    }

    @GetMapping("/books/all")
    public Response getSearchedBook(@RequestParam ("search") String search,
                                    @RequestParam ("sort") String sort,
                                    @RequestParam ("page") int selectedPage) {
        return bookService.getBooks(search,sort,selectedPage);
    }
}
