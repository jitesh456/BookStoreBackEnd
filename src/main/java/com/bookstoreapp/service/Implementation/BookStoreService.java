package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.dto.BookStoreDto;
import com.bookstoreapp.exception.BookStoreException;
import com.bookstoreapp.modal.BookStore;
import com.bookstoreapp.repository.IBookStoreRepository;
import com.bookstoreapp.service.IBookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookStoreService implements IBookStoreService {

    @Autowired
    IBookStoreRepository iBookStoreRepository;

    @Override
    public String addBook(BookStoreDto bookStoreDto) {
        BookStore bookStore=new BookStore(bookStoreDto);
        iBookStoreRepository.save(bookStore);
            return "Insertion Successful";
    }
}
