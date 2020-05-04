package com.bookstoreapp.controller;

import com.bookstoreapp.dto.BookStoreDto;
import com.bookstoreapp.service.IBookStoreService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class BookStoreControllerTest {

    @Autowired
    BookStoreController bookStoreControllerTest;

    @MockBean
    IBookStoreService iBookStoreService;

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
        bookStoreDto=new BookStoreDto();
        bookStoreDto.setName("Rajnish");
        bookStoreDto.setPrice(12000.0);
        bookStoreDto.setQuantity(12);
        bookStoreDto.setBookCover("dsfsdfsf");
        bookStoreDto.setCategory("comics");
        bookStoreDto.setAuthorName("Jitesh");
        bookStoreDto.setBookDetails("kjvcgvhbjklkbjvh");
        bookStoreDto.setIsbn("ABCD1");

        Gson gson=new Gson();

        given(iBookStoreService.addBook(any())).willReturn("Insertion Successful");
        String s = gson.toJson(bookStoreDto);

        ResultActions perform = mockMvc.perform(post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(s))).andExpect(content().string("Insertion Successful"));
    }



}
