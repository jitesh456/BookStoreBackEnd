package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.exception.BookException;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.repository.IBookRepository;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    public Response getSearchedBook(String search) {
        Response response = null;
        List<Book> searchedBook= (List<Book>) iBookRepository.SearchBook(search);
        if(searchedBook.size()>0)
            return new Response("Book List is Searched On basic of given search",200,searchedBook);

        return new Response("Books Not Found",200,searchedBook);
    }
}