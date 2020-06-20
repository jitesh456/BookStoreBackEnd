package com.bookstoreapp.response;

import com.bookstoreapp.model.Book;
import java.util.List;

public class BookResponse {

    public List<Book> books;

    public int count;

    public BookResponse(List<Book> books, int count) {
        this.books = books;
        this.count = count;
    }
}
