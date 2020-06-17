package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.comparison.Comparison;
import com.bookstoreapp.exception.BookException;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.repository.IBookRepository;
import com.bookstoreapp.response.BookResponse;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService implements IBookService {

    private int count=0;
    private int perPage=8;

    @Autowired
    IBookRepository bookRepository;

    public BookService() {
        count=0;
    }

    @Override
    public Iterable<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public Iterable<Book> getSortedBook(String sortField) {
        if(sortField != null) {
            return bookRepository.findAll(Sort.by(Sort.Direction.ASC, sortField));
        }
        throw new BookException("SORT FIELD CAN NOT NULL",BookException.ExceptionType.SORT_FIELD_CAN_NOT_NULL);
    }

    @Override
    public Response getBooks(String search, String sort,int page) {
        Pageable pageable=this.getPage(page);
        List<Book> books = this.searchSort(search,sort,pageable);
        if (count > 0)
            return new Response("Books Found On Given Search or Sort Fields", 200,
                    new BookResponse(books,count));

        return new Response("Books Not Found", 200, new BookResponse(books,count));
    }

    private Pageable getPage(int page) {
        int start=page*perPage;
        int end=start+perPage;
        return PageRequest.of(start,end);
    }

    private List<Book> searchSort(String search, String sort, Pageable pageable) {
        count= (int) bookRepository.searchBook(pageable,search).getTotalElements();
        List<Book> books = bookRepository.searchBook(pageable,search).getContent();
        books = Comparison.getSortedBooks(books, sort.toLowerCase().trim());
        return books;
    }
}