package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.dto.BookStoreDto;
import com.bookstoreapp.service.IBookStoreService;
import org.springframework.stereotype.Service;

@Service
public class BookStoreService implements IBookStoreService {
    @Override
    public String addBook(BookStoreDto bookStoreDto) {
        return "Insertion Successful";
    }
}
