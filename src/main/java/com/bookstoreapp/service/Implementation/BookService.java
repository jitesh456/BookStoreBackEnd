package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.comparison.Comparison;
import com.bookstoreapp.exception.BookException;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.repository.IBookRepository;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BookService implements IBookService {

    @Autowired
    IBookRepository iBookRepository;

    public BookService() {
    }

    @Override
    public Iterable<Book> getAllBook() {
        return iBookRepository.findAll();
    }

    @Override
    public Iterable<Book> getSortedBook(String sortField) {
        if(sortField != null) {
            return iBookRepository.findAll(Sort.by(Sort.Direction.ASC, sortField));
        }
        throw new BookException("SORT FIELD CAN NOT NULL",BookException.ExceptionType.SORT_FIELD_CAN_NOT_NULL);
    }

    @Override
    public Response getBooks(String search, String sort) {
        List<Book> books = null;
        books = (List<Book>) iBookRepository.SearchBook(search);
        books = Comparison.getSortedBooks(books, sort.toLowerCase().trim());
        if (books.size() > 0)
            return new Response("Books Found On Given Search or Sort Fields", 200, books);
        return new Response("Books Not Found", 200, books);
    }
}