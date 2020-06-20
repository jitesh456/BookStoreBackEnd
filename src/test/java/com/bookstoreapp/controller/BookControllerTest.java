package com.bookstoreapp.controller;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.exception.BookException;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.BookService;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = BookController.class)
@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @MockBean
    BookService bookService;

    BookDto bookDto;

    @Autowired
    MockMvc mockMvc;

    Gson gson;

    @BeforeEach
    void setUp() {

        gson = new Gson();
        bookDto = new BookDto("Secret of nagas", 2000.0,
                12, "Amish Tiwari", "comic",
                "987564236578", "sdfsfd", "Adaptation of the first of J.K. Rowling's popular " +
                "children's novels about Harry Potter, a boy who learns on his eleventh birthday that he is the orphaned son ");
    }


    @Test
    void whenBookFound_ShouldReturnProperMessage() throws Exception {

        BookDto bookDto1 = new BookDto("Naruto", 200.0,
                20, "makashi kissimoto", "Manga",
                "12345678", "", "story about ninja boy ");
        Book book = new Book(bookDto);
        Book book1 = new Book(bookDto1);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book1);
        Mockito.when(bookService.getAllBook()).thenReturn(bookList);
        String expectedList = gson.toJson(bookList);
        MvcResult result = this.mockMvc.perform(get("/books")).andReturn();
        Assert.assertEquals(200, result.getResponse().getStatus());
        Assert.assertEquals("Fetched Books", gson.fromJson(result.getResponse().
                getContentAsString(), Response.class).message);
    }

    @Test
    void givenSortSearchAndPage_WhenProper_shouldReturnBook() throws Exception {

        Response response=new Response("BookList base on search sot field",200,"");
        MultiValueMap<String,String> params=new LinkedMultiValueMap<>();
        params.put("search", Collections.singletonList("a"));
        params.put("sort", Collections.singletonList("authorName"));
        params.put("page", Collections.singletonList("2"));
        Mockito.when(bookService.getBooks(anyString(),anyString(),anyInt())).thenReturn(response);
        MvcResult result = this.mockMvc.perform(get("/books/all").params(params)).andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("BookList base on search sot field",gson.fromJson(result.getResponse().getContentAsString(),Response.class).message);
    }

    @Test
    void givenSortSearchAndPage_WhenSearchField_IsNull_shouldReturnBook() throws Exception {

        MultiValueMap<String,String> params=new LinkedMultiValueMap<>();
        params.put("search", Collections.singletonList(""));
        params.put("sort", Collections.singletonList("authorName"));
        params.put("page", Collections.singletonList("2"));
        Mockito.when(bookService.getBooks(anyString(),anyString(),anyInt())).
                thenThrow(new BookException("Books Not Found", BookException.ExceptionType.BOOK_NOT_FOUND));
        MvcResult result = this.mockMvc.perform(get("/books/all").params(params)).andReturn();
        Assert.assertEquals(404,result.getResponse().getStatus());
        Assert.assertEquals("Books Not Found",gson.fromJson(result.getResponse().getContentAsString(),Response.class).message);
    }
}