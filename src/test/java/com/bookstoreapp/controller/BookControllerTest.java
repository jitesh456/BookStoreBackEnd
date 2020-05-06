package com.bookstoreapp.controller;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.modal.Book;
import com.bookstoreapp.response.ResponseDto;
import com.bookstoreapp.service.IBookService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    BookController bookControllerTest;

    @MockBean
    IBookService ibookService;

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
        bookDto =new BookDto("Rajnish",2000.0,
                12,"dfsdfsf","comic",
                "Jitesh","sdfsfd","ABCD");
        gson=new Gson();
    }

    @Test
    void givenBookData_WhenInserted_ReturnProperMessage() throws Exception {

        String bookStoreDto=new Gson().toJson(this.bookDto);
        Mockito.when(ibookService.addBook(any())).thenReturn("Inserted Successful");
        MvcResult result = this.mockMvc.perform(post("/admin/update/book")
                .content(bookStoreDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Inserted",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDto.class).message);
    }



    @Test
    void givenBookData_WhenNull_ReturnProperMessage() throws Exception {
       BookDto bookDto1 =new BookDto("Rajnish",2000.0,
                12,null,"comic",
                "Jitesh","sdfsfd","ABCD");

        String bookStoreDtoString = gson.toJson(bookDto1);
        MvcResult result = this.mockMvc.perform(post("/admin/update/book")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("author name should not be null",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDto.class).message);
    }


    @Test
    void givenWrongUrlPath_WhenChecked_ShouldReturnIncorrectUrlMessage() throws Exception {

        String json=gson.toJson(bookDto);
        this.mockMvc.perform(post("/admin/book").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenWrongContentType_WhenChecked_ShouldReturnUnSupportedTypeException() throws Exception {

        String json=gson.toJson(bookDto);
        this.mockMvc.perform(post("/admin/update/book").content(json)
                .contentType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void givenIncorrectMethod_WhenChecked_ShouldReturnMethodNotAllowed() throws Exception {

        String json=gson.toJson(bookDto);
        this.mockMvc.perform(get("/admin/update/book").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void getAllData() throws Exception {
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
        MvcResult result = this.mockMvc.perform(get("/admin/books")).andReturn();
        Assert.assertEquals(302,result.getResponse().getStatus());
        Assert.assertEquals("Request Success",gson.fromJson(result.getResponse().getContentAsString(),ResponseDto.class).message);
    }

}
