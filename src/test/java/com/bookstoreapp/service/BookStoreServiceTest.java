package com.bookstoreapp.service;

import com.bookstoreapp.dto.BookStoreDto;
import com.bookstoreapp.modal.BookStore;
import com.bookstoreapp.repository.IBookStoreRepository;
import com.bookstoreapp.service.Implementation.BookStoreService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookStoreServiceTest {

  @Mock
  IBookStoreRepository iBookStoreRepository;

  @InjectMocks
    BookStoreService bookStoreService;

  BookStoreDto bookStoreDto;

    @Test
    void givenBookDetails_WhenAddedInStore_ShouldReturnAddedBook() {
        bookStoreDto=new BookStoreDto();
        bookStoreDto.setName("Rajnish");
        bookStoreDto.setPrice(12000.0);
        bookStoreDto.setQuantity(12);
        bookStoreDto.setBookcover("dsfsdfsf");
        bookStoreDto.setCategory("comics");
        bookStoreDto.setAuthorname("Jitesh");
        bookStoreDto.setBookdetails("kjvcgvhbjklkbjvh");
        bookStoreDto.setIsbn("ABCD1");
        BookStore givenBook=new BookStore(bookStoreDto);
        String expectedresponse="Insertion Successful";
        when(iBookStoreRepository.save(any())).thenReturn(givenBook);
        String addedBook=bookStoreService.addBook(bookStoreDto);
        Assert.assertEquals(expectedresponse,addedBook);
    }
}
