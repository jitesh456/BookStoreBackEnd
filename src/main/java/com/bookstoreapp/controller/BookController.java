package com.bookstoreapp.controller;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.CartDto;
import com.bookstoreapp.model.Book;
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
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public Response getAllData(){
        Iterable<Book> allBook = bookService.getAllBook();
        Response response=new Response("Fetched Books",200,allBook);
        return response;
    }

    @GetMapping("/books/field")
    public Response getSortedBook(@RequestParam ("field")String field){
        if(field.isEmpty())
        {
            return  new Response("Field cant be null for sorting",400,null);
        }
        Iterable<Book> sortedBook = bookService.getSortedBook(field);
        Response response=new Response("Book List is Sorted On basic of given field",200,sortedBook);
        return response;
    }

    @PostMapping("/book")
    public ResponseEntity<Response> addBook(@Valid @RequestBody CartDto cartDto,
                                            BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),101,"Empty Field"), HttpStatus.BAD_REQUEST);
        }
        String responseMessage= bookService.addToCart(cartDto);
        return new ResponseEntity<Response>(new Response("Book Added To Cart",200, responseMessage),
                HttpStatus.OK);
    }
}
