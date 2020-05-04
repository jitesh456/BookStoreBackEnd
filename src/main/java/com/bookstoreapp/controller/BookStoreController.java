package com.bookstoreapp.controller;

import com.bookstoreapp.dto.BookStoreDto;
import com.bookstoreapp.service.IBookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookStoreController {


    @Autowired
    IBookStoreService iBookStoreService;
    @PostMapping("/add")
    public String addBook(@RequestBody BookStoreDto bookStoreDto)
    {
        return iBookStoreService.addBook(bookStoreDto);
    }
}
