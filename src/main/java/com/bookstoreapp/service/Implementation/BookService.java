package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.UpdateBookDto;
import com.bookstoreapp.exception.BookException;
import com.bookstoreapp.modal.Book;
import com.bookstoreapp.repository.IBookRepository;
import com.bookstoreapp.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    IBookRepository iBookRepository;

    public BookService() {
    }

    @Override
    public String addBook(BookDto bookDto) {
        Book book =new Book(bookDto);
        Optional<Book> findBookByIsbn=iBookRepository.findByIsbn(bookDto.getIsbn());
        if (findBookByIsbn.isPresent()){
            throw new BookException("BOOK ALREADY EXISTS",BookException.ExceptionType.BOOK_ALREADY_EXIST);
        }
        iBookRepository.save(book);
            return "Insertion Successful";
    }

    @Override
    public Iterable<Book> getAllBook() {
        return iBookRepository.findAll();
    }

    @Override
    public String updatePrice(UpdateBookDto bookDto) {
        Optional<Book> findBookByIsbn=iBookRepository.findByIsbn(bookDto.getIsbn());
        if (findBookByIsbn.isPresent()){
            Book book=findBookByIsbn.get();
            book.setPrice(bookDto.getPrice());
            book.setQuantity(bookDto.getQuantity());
            iBookRepository.save(book);
            return "Updated Successfully";
        }
        throw new BookException("BOOK DOES NOT EXISTS",BookException.ExceptionType.BOOK_DOES_NOT_EXIST);
    }
}
