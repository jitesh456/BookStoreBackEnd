package com.bookstoreapp.service;


import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.UpdateBookDto;
import com.bookstoreapp.dto.UpdateCartDto;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.repository.IBookRepository;
import com.bookstoreapp.service.Implementation.AdminService;
import com.bookstoreapp.util.implementation.SendMail;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AdminServiceTest {

    @Mock
    IBookRepository iBookRepository;

    @MockBean
    SendMail mailSender;

    @InjectMocks
    AdminService adminService;
    BookDto bookDto;

    @BeforeEach
    void setUp() {
        bookDto =new BookDto("Secret of nagas",2000.0,
                12,"Amish Tiwari","comic",
                "998542365","sdfsfd","ABCD");
    }


    @Test
    void givenBookDetails_WhenAddedInStore_ShouldReturnAddedBook() {
        Book givenBook=new Book(bookDto);
        String expectedresponse="Insertion Successful";
        when(iBookRepository.save(any())).thenReturn(givenBook);
        String addedBook= adminService.addBook(bookDto);
        Assert.assertEquals(expectedresponse,addedBook);


    }

    @Test
    void givenBookDataPrice_WhenUpdated_ReturnProperMessage() throws Exception {
        Book givenBook=new Book(bookDto);

        UpdateBookDto bookDto1 =new UpdateBookDto(2450.0, "1234567895",12);
        String expectedresponse="Updated Successfully";
        when(iBookRepository.findByIsbn(any())).thenReturn(java.util.Optional.of(givenBook));
        when(iBookRepository.save(any())).thenReturn(givenBook);
        String actualresponse= adminService.updatePrice(bookDto1);
        Assert.assertEquals(expectedresponse,actualresponse);
    }


    @Test
    void givenQuantity_WhenProper_ShouldUpdateBookQuantity() {
        Book givenBook = new Book(bookDto);

        UpdateCartDto updateCartDto = new UpdateCartDto("1234567895", 5);
        String expectedresponse = "Book Quantity Updated";
        when(iBookRepository.findByIsbn(any())).thenReturn(java.util.Optional.of(givenBook));
        when(iBookRepository.save(any())).thenReturn(givenBook);
        String actualresponse = adminService.updateQuantity(updateCartDto);
        Assert.assertEquals(expectedresponse, actualresponse);
    }
}
