package com.bookstoreapp.service;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.repository.IBookRepository;
import com.bookstoreapp.response.BookResponse;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.BookService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class BookServiceTest {

    @Mock
    IBookRepository iBookRepository;

    @InjectMocks
    BookService bookService;

    BookDto bookDto;

    @BeforeEach
    void setUp() {
        bookDto =new BookDto("Secret of nagas",2000.0,
                12,"Amish Tiwari","comic",
                "998542365","sdfsfd","ABCD");
    }

    @Test
    void whenBookDetailsFound_ShouldReturnAllBookDetails(){

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
        Assert.assertEquals(allBook,bookIterable);

    }

    @Test
    void givenSearchSortField_whenProper_ShouldReturn_Books() {
        Book book=new Book(bookDto);
        List<Book> books=new ArrayList<>();
        books.add(book);
        Mockito.when(iBookRepository.searchBook(anyString())).thenReturn(books);
        Response response=bookService.getBooks("A","authorName",0);
        Assert.assertEquals("Books Found On Given Search or Sort Fields",response.message);
        Assert.assertEquals(200,response.statusCode);
    }
}
