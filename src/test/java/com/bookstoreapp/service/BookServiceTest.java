package com.bookstoreapp.service;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.modal.Book;
import com.bookstoreapp.repository.IBookRepository;
import com.bookstoreapp.service.Implementation.BookService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {

  @Mock
  IBookRepository iBookRepository;

  @InjectMocks
  BookService bookStoreService;

  BookDto bookDto;

    @Test
    void givenBookDetails_WhenAddedInStore_ShouldReturnAddedBook() {

        bookDto =new BookDto("Rajnish",2000.0,
                12,"dfsdfsf","comic",
                "Jitesh","sdfsfd","ABCD");
        Book givenBook=new Book(bookDto);
        String expectedresponse="Insertion Successful";
        when(iBookRepository.save(any())).thenReturn(givenBook);
        String addedBook=bookStoreService.addBook(bookDto);
        Assert.assertEquals(expectedresponse,addedBook);
    }
}
