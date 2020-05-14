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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class BookControllerTest {

    @MockBean
    BookService ibookService;

    BookDto bookDto;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TestRestTemplate testRestTemplate;

    HttpHeaders headers;

    Gson gson;

    @BeforeEach
    void setUp() {
        headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        gson=new Gson();
        bookDto =new BookDto("Secret of nagas",2000.0,
                12,"Amish Tiwari","comic",
                "987564236578","sdfsfd","Adaptation of the first of J.K. Rowling's popular " +
                "children's novels about Harry Potter, a boy who learns on his eleventh birthday that he is the orphaned son " );
    }


    @Test
    void whenBookFound_ShouldReturnProperMessage() throws Exception {
        BookDto bookDto1 =new BookDto("Naruto",200.0,
                20,"makashi kissimoto","Manga",
                "12345678","","story about ninja boy ");
        Book book =new Book(bookDto);
        Book book1 =new Book(bookDto1);
        List<Book> bookList =new ArrayList<>();
        bookList.add(book);
        bookList.add(book1);
        Mockito.when(ibookService.getAllBook()).thenReturn(bookList);
        String expectedList = gson.toJson(bookList);
        MvcResult result = this.mockMvc.perform(get("/books")).andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Fetched Books",gson.fromJson(result.getResponse().
                getContentAsString(), Response.class).message);
    }

    @Test
    void givenPrice_WhenProper_ShouldReturnAllBookHavingPriceLessThenGivenPrice() throws Exception {
        BookDto bookDto1 =new BookDto("Naruto",200.0,
                20,"makashi kissimoto","Manga",
                "12345678","","story about ninja boy ");
        Book book =new Book(bookDto);
        Book book1 =new Book(bookDto1);
        List<Book> bookList =new ArrayList<>();
        bookList.add(book1);
        Mockito.when(ibookService.getSortedBook(any())).thenReturn(bookList);
        String expectedList=gson.toJson(bookList);
        MvcResult result=this.mockMvc.perform(get("/books/field?field=price")).andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Book List is Sorted On basic of given field",gson.fromJson(result.getResponse()
                .getContentAsString(), Response.class).message);
    }


    @Test
    void givenPrice_WhenNull_ShouldReturnErrorMessage() throws Exception {
        BookDto bookDto1 =new BookDto("Naruto",200.0,
                20,"makashi kissimoto","Manga",
                "12345678","","story about ninja boy ");
        Book book =new Book(bookDto);
        Book book1 =new Book(bookDto1);
        List<Book> bookList =new ArrayList<>();
        bookList.add(book1);
        Mockito.when(ibookService.getSortedBook(any())).thenReturn(bookList);
        String expectedList=gson.toJson(bookList);
        MvcResult result=this.mockMvc.perform(get("/books/field?field=")).andReturn();
        Assert.assertEquals(400,gson.fromJson(result.getResponse()
                .getContentAsString(), Response.class).statusCode);
        Assert.assertEquals("Field cant be null for sorting",gson.fromJson(result.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    void givenASortField_WhenNull_ShouldThrowException()throws Exception {
        try{
                BookDto bookDto1 = new BookDto("Naruto", 200.0,
                        20, "makashi kissimoto", "Manga",
                        "12345678", "", "story about ninja boy ");
                Book book = new Book(bookDto);
                Book book1 = new Book(bookDto1);
                List<Book> bookList = new ArrayList<>();
                bookList.add(book);
                bookList.add(book1);
                Mockito.when(ibookService.getSortedBook(any())).thenThrow( new BookException("SORT FIELD CAN NOT NULL",BookException.ExceptionType.SORT_FIELD_CAN_NOT_NULL));
                MvcResult result = this.mockMvc.perform(get("/books/field?field=")).andReturn();
        }catch(BookException e){
            Assert.assertEquals(BookException.ExceptionType.SORT_FIELD_CAN_NOT_NULL,e.exceptionType);}
    }

}
