package com.bookstoreapp.controller;

import com.bookstoreapp.dto.BookStoreDto;
import com.bookstoreapp.response.ResponseDto;
import com.bookstoreapp.service.IBookStoreService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class BookStoreControllerTest {

    @Autowired
    BookStoreController bookStoreControllerTest;

    @MockBean
    IBookStoreService ibookStoreService;

    BookStoreDto bookStoreDto;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TestRestTemplate testRestTemplate;

    HttpHeaders headers;

    @BeforeEach
    void setUp() {
        headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

    }

    @Test
    void givenBookData_WhenInserted_ReturnProperMessage() throws Exception {
        bookStoreDto=new BookStoreDto("Rajnish",2000.0,
                12,"dfsdfsf","comic",
                "Jitesh","sdfsfd","ABCD");

        String bookStoreDto=new Gson().toJson(this.bookStoreDto);

        Mockito.when(ibookStoreService.addBook(any())).thenReturn("Inserted Successful");

        MvcResult result = this.mockMvc.perform(post("/add")
                .content(bookStoreDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Inserted",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDto.class).message);
    }



    @Test
    void givenBookData_WhenNull_ReturnProperMessage() throws Exception {
        bookStoreDto=new BookStoreDto("Rajnish",2000.0,
                12,null,"comic",
                "Jitesh","sdfsfd","ABCD");
        Gson gson=new Gson();
        String bookStoreDtoString = gson.toJson(bookStoreDto);
        MvcResult result = this.mockMvc.perform(post("/add")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("author name should not be null",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDto.class).message);
    }


}
