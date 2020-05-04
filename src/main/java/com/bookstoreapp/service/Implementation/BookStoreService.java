package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.dto.BookStoreDto;
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
        BookStore bookStore=new BookStore();
        bookStore.setName(bookStoreDto.getName());
        bookStore.setPrice(bookStoreDto.getPrice());
        bookStore.setQuantity(bookStoreDto.getQuantity());
        bookStore.setBookcover(bookStoreDto.getBookcover());
        bookStore.setCategory(bookStoreDto.getCategory());
        bookStore.setAuthorname(bookStoreDto.getAuthorname());
        bookStore.setBookdetails(bookStoreDto.getBookdetails());
        bookStore.setIsbn(bookStoreDto.getIsbn());
        iBookStoreRepository.save(bookStore);
        return "Insertion Successful";
    }
}
