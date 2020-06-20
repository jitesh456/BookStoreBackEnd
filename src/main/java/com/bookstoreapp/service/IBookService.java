package com.bookstoreapp.service;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.response.Response;

public interface IBookService {

    Iterable<Book> getAllBook();

    Response getBooks(String search, String sort,int page);
}
