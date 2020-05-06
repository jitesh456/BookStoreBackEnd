package com.bookstoreapp.service;

import com.bookstoreapp.dto.BookStoreDto;
import com.bookstoreapp.modal.BookStore;

public interface IBookStoreService {

    String addBook(BookStoreDto bookStoreDto);

    Iterable<BookStore> getAllBook();
}
