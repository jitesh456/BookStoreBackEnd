package com.bookstoreapp.service;
import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.NotificationDto;
import com.bookstoreapp.dto.UpdateBookDto;
import com.bookstoreapp.dto.UpdateCartDto;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.response.FileResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

public interface IBookService {

    Iterable<Book> getAllBook();

    Iterable<Book> getSortedBook(String sortBookDto);



}
