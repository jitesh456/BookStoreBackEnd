package com.bookstoreapp.service;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.modal.Book;

public interface IBookService {

    String addBook(BookDto bookDto);

    Iterable<Book> getAllBook();
}
