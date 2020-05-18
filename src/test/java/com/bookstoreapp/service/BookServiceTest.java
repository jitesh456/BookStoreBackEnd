package com.bookstoreapp.service;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.CartDto;
import com.bookstoreapp.dto.UpdateBookDto;
import com.bookstoreapp.dto.UpdateCartDto;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.model.Cart;
import com.bookstoreapp.repository.IBookRepository;
import com.bookstoreapp.repository.ICartRepository;
import com.bookstoreapp.service.Implementation.BookService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
public class BookServiceTest {

    @Mock
    IBookRepository iBookRepository;

    @Mock
    ICartRepository iCartRepository;
    @InjectMocks
    BookService bookService;

    BookDto bookDto;
    CartDto cartDto;
    @BeforeEach
    void setUp() {
        bookDto =new BookDto("Secret of nagas",2000.0,
                12,"Amish Tiwari","comic",
                "998542365","sdfsfd","ABCD");
        cartDto=new CartDto("Secret of nagas",2000.0,12,"Amish Tiwari","987564236578","imagesrc");
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

    @Test
    void givenSortField_WhenProper_ShouldReturnSortedBooksBasedOnPrice(){
        BookDto bookDto1 =new BookDto("Naruto",200.0,
                20,"makashi kissimoto","Manga",
                "12345678","","story about ninja boy ");
        Book book =new Book(bookDto);
        Book book1 =new Book(bookDto1);
        List<Book> bookList =new ArrayList<>();
        bookList.add(book1);
        bookList.add(book);
        Iterable<Book> bookIterable=bookList;
        Mockito.when(iBookRepository.findAll(Sort.by(Sort.Direction.ASC, "price"))).thenReturn((List<Book>) bookIterable);
        Iterable<Book> sortedBooks=bookService.getSortedBook("price");
        Assert.assertEquals(sortedBooks,bookIterable);
    }

    @Test
    void givenBookData_WhenProper_ShouldAddToCart() {
        Cart book=new Cart(cartDto);
        Mockito.when(iCartRepository.save(any())).thenReturn(book);
        String expectedMessage = bookService.addToCart(cartDto);
        Assert.assertEquals("Book Added To Cart",expectedMessage);
    }

    @Test
    void givenQuantityAndPrice_WhenProper_ShouldUpdateBookQuantity()
    {
        UpdateCartDto updateCartDto=new UpdateCartDto("1234567895",14);
        Cart book=new Cart(cartDto);
        Mockito.when(iCartRepository.findByIsbn(any())).thenReturn(java.util.Optional.of(book));
        String expectedMessage = bookService.updateQuantity(updateCartDto);
        Assert.assertEquals("Book Quantity Updated",expectedMessage);
    }
}
