package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.comparison.Comparison;
import com.bookstoreapp.exception.BookException;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.repository.IBookRepository;
import com.bookstoreapp.response.BookResponse;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Response getBooks(String search, String sort,int page) {

        List<Book> books = this.searchSort(search,sort);
        books=this.getAllBooks(books);
        books=this.getPage(books,page);
        if (books.size() > 0)
            return new Response("Books Found On Given Search or Sort Fields", 200,
                    new BookResponse(books,count));
        throw new BookException("Books Not Found", BookException.ExceptionType.BOOK_NOT_FOUND);
    }

    private List<Book> getAllBooks(List<Book> books) {

        if(books.size()==0){
            books= (List<Book>) this.getAllBook();
            count=books.size();
        }
        return books;
    }

    private List<Book> getPage(List<Book> books, int page) {

        int start=page*perPage;
        int end=start+perPage;
        if(end<books.size())
            return books.subList(start,end);
        return books.subList(start,books.size());
    }

    private List<Book> searchSort(String search, String sort) {

        List<Book> books = bookRepository.searchBook(search);
        books=Comparison.getSortedBooks(books,sort.toLowerCase().trim());
        count=books.size();
        return books;
    }
}