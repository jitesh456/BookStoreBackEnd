package com.bookstoreapp.service;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.response.Response;

public interface IBookService {

    Iterable<Book> getAllBook();

    Iterable<Book> getSortedBook(String sortBookDto);

    Response getSearchedBook(String search);
}
