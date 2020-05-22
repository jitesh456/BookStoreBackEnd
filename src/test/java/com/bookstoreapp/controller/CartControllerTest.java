package com.bookstoreapp.controller;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.NotificationDto;
import com.bookstoreapp.dto.UpdateCartDto;
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

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class CartControllerTest {
    @MockBean
    BookService bookService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TestRestTemplate testRestTemplate;

    HttpHeaders headers;

    Gson gson;

    @BeforeEach
    void setUp() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        gson = new Gson();
    }

    @Test
    void givenBookQuantity_WhenUpdated_ShouldReturnProperMessage() throws Exception {
        UpdateCartDto updateCartDto = new UpdateCartDto("1234567895", 14);
        String cartDtoString = gson.toJson(updateCartDto);
        Mockito.when(bookService.updateQuantity(any())).thenReturn("Book Quantity Updated");
        MvcResult result = this.mockMvc.perform(put("/book")
                .content(cartDtoString)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertEquals(200, result.getResponse().getStatus());
        Assert.assertEquals("Book Quantity Updated",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);
    }

    @Test
    void givenEmailAddress_WhenMailSent_ReturnProperMessage() throws Exception {
        NotificationDto notificationDto=new NotificationDto("rajnish.kahar1996@gmail.com","Test","Hello User");
        String notificationString=gson.toJson(notificationDto);
        Mockito.when(bookService.sendMail(any())).thenReturn("Mail Sent Successfully");
        MvcResult result=this.mockMvc.perform(post("/mail")
                .content(notificationString)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Mail Sent Successfully",
                new Gson().fromJson(result.getResponse().getContentAsString(),Response.class).message);
    }
}
