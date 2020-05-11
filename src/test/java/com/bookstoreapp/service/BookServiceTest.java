package com.bookstoreapp.service;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.UpdateBookDto;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.repository.IBookRepository;
import com.bookstoreapp.service.Implementation.BookService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
public class BookServiceTest {

  @Mock
  IBookRepository iBookRepository;

  @InjectMocks
  BookService bookService;

  BookDto bookDto;

    @BeforeEach
    void setUp() {
        bookDto =new BookDto("Rajnish",2000.0,
                12,"dfsdfsf","comic",
                "Jitesh","sdfsfd","ABCD");
    }

    @Test
    void givenBookDetails_WhenAddedInStore_ShouldReturnAddedBook() {
        Book givenBook=new Book(bookDto);
        String expectedresponse="Insertion Successful";
        when(iBookRepository.save(any())).thenReturn(givenBook);
        String addedBook=bookService.addBook(bookDto);
        Assert.assertEquals(expectedresponse,addedBook);
    }

    @Test
    void getAllBook(){

        BookDto bookDto1 =new BookDto("Naruto",200.0,
                20,"makashi kissimoto","Manga",
                "12345678","","story about ninja boy ");
        Book book =new Book(bookDto);
        Book book1 =new Book(bookDto1);
        List<Book> bookList =new ArrayList<>();
        bookList.add(book);
        bookList.add(book1);
        Iterable<Book> bookIterable=bookList;
        Mockito.when(iBookRepository.findAll()).thenReturn((List<Book>) bookIterable);
        Iterable<Book> allBook = bookService.getAllBook();
        Assert.assertEquals(allBook,bookIterable);

    }

    @Test
    void givenBookDataPrice_WhenUpdated_ReturnProperMessage() throws Exception {
        Book givenBook=new Book(bookDto);

        UpdateBookDto bookDto1 =new UpdateBookDto(2450.0, "1234567895",12);
        String expectedresponse="Updated Successfully";
        when(iBookRepository.findByIsbn(any())).thenReturn(java.util.Optional.of(givenBook));
        when(iBookRepository.save(any())).thenReturn(givenBook);
        String actualresponse=bookService.updatePrice(bookDto1);
        Assert.assertEquals(expectedresponse,actualresponse);
    }
}
