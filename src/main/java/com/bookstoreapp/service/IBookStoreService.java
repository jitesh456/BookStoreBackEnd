package com.bookstoreapp.service;

import com.bookstoreapp.dto.BookStoreDto;
import com.bookstoreapp.modal.BookStore;

import java.util.List;

public interface IBookStoreService {

    String addBook(BookStoreDto bookStoreDto);

    List<BookStore> getAllBook();
}
