package com.bookstoreapp.service;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.NotificationDto;
import com.bookstoreapp.dto.UpdateBookDto;
import com.bookstoreapp.dto.UpdateCartDto;
import com.bookstoreapp.model.Book;

import javax.mail.MessagingException;

public interface IBookService {

    String addBook(BookDto bookDto);

    Iterable<Book> getAllBook();

    String updatePrice(UpdateBookDto bookDto);

    Iterable<Book> getSortedBook(String sortBookDto);

    String updateQuantity(UpdateCartDto updateCartDto);

    String sendMail(NotificationDto notificationDto) throws MessagingException;
}
