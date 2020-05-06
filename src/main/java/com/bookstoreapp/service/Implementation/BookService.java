package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.modal.Book;
import com.bookstoreapp.repository.IBookRepository;
import com.bookstoreapp.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService {

    @Autowired
    IBookRepository iBookRepository;

    @Override
    public String addBook(BookDto bookDto) {
        Book book =new Book(bookDto);
        iBookRepository.save(book);
            return "Insertion Successful";
    }

    @Override
    public Iterable<Book> getAllBook() {
        return iBookRepository.findAll();
    }
}
