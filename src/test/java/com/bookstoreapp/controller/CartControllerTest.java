package com.bookstoreapp.controller;

import com.bookstoreapp.dto.AddToCartDto;
import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.NotificationDto;
import com.bookstoreapp.dto.UpdateCartDto;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.BookService;
import com.bookstoreapp.service.Implementation.CartService;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(controllers = CartController.class)
@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {
    @MockBean
    BookService bookService;

    @Autowired
    MockMvc mockMvc;

    Gson gson;

    @Autowired
    CartController cartController;

    @MockBean
    CartService cartService;

    HttpHeaders httpHeaders=new HttpHeaders();
    BookDto bookDto;

    @BeforeEach
    void setUp() {
        httpHeaders.set("Token","abcdef1234");
        gson = new Gson();
        bookDto =new BookDto("Secret of nagas", 2000.0,
                12, "Amish Tiwari", "comic",
                "987564236578", "sdfsfd", "Adaptation of the first of J.K. Rowling's popular " +
                "children's novels about Harry Potter, a boy who learns on his eleventh birthday that he is the orphaned son ");
    }

    @Test
    void givenBookQuantity_WhenUpdated_ShouldReturnProperMessage() throws Exception {
        UpdateCartDto updateCartDto = new UpdateCartDto("12345678951", 14);
        String cartDtoString = gson.toJson(updateCartDto);
        Mockito.when(bookService.updateQuantity(any())).thenReturn("Book Quantity Updated");
        MvcResult result = this.mockMvc.perform(put("/book")
                .content(cartDtoString)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .headers(httpHeaders))
                .andReturn();
        Assert.assertEquals(200, result.getResponse().getStatus());
        Assert.assertEquals("Book Quantity Updated",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);
    }

    @Test
    void givenBookQuantity_WhenISBNNull_ShouldReturnProperMessage() throws Exception {
        UpdateCartDto updateCartDto = new UpdateCartDto(null, 14);
        String cartDtoString = gson.toJson(updateCartDto);
        Mockito.when(bookService.updateQuantity(any())).thenReturn("Book Quantity Updated");
        MvcResult result = this.mockMvc.perform(put("/book")
                .content(cartDtoString)
                .contentType("application/json")
                .characterEncoding("utf-8")
                .headers(httpHeaders))
                .andReturn();
        Assert.assertEquals(400, result.getResponse().getStatus());
        Assert.assertEquals("ISBN  should not be null",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);
    }

    @Test
    void givenBookQuantity_WhenISBNNullLessThenElevenDigit_ShouldReturnProperMessage() throws Exception {
        UpdateCartDto updateCartDto = new UpdateCartDto("123456789", 14);
        String cartDtoString = gson.toJson(updateCartDto);
        Mockito.when(bookService.updateQuantity(any())).thenReturn("Book Quantity Updated");
        MvcResult result = this.mockMvc.perform(put("/book")
                .content(cartDtoString)
                .contentType("application/json")
                .characterEncoding("utf-8")
                .headers(httpHeaders))
                .andReturn();
        Assert.assertEquals(400, result.getResponse().getStatus());
        Assert.assertEquals("ISBN must have 11 Digit",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);
    }
    @Test
    void givenBookQuantity_WhenQuantityIsNegative_ShouldReturnProperMessage() throws Exception {
        UpdateCartDto updateCartDto = new UpdateCartDto("12345678912", -1);
        String cartDtoString = gson.toJson(updateCartDto);
        Mockito.when(bookService.updateQuantity(any())).thenReturn("Book Quantity Updated");
        MvcResult result = this.mockMvc.perform(put("/book")
                .content(cartDtoString)
                .contentType("application/json")
                .characterEncoding("utf-8")
                .headers(httpHeaders))
                .andReturn();
        Assert.assertEquals(400, result.getResponse().getStatus());
        Assert.assertEquals("Quantity cant be less then 0",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);
    }


    @Test
    void givenEmailAddress_WhenMailSent_ReturnProperMessage() throws Exception {
        NotificationDto notificationDto=new NotificationDto("rajnish.kahar1996@gmail.com","Test","Hello User");
        String notificationString=gson.toJson(notificationDto);
        Mockito.when(bookService.sendMail(any())).thenReturn("Mail Sent Successfully");

        MvcResult result=this.mockMvc.perform(post("/mail")
                .content(notificationString)
                .contentType("application/json")
                .characterEncoding("utf-8")
                .headers(httpHeaders))

                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Mail Sent Successfully",
                new Gson().fromJson(result.getResponse().getContentAsString(),Response.class).message);
    }

    @Test
    void givenCartDetails_WhenProper_ShouldReturnProperMessage() throws Exception {
        Response response=new Response("Book is Added To Cart",200,"");
        AddToCartDto addToCartDto=new AddToCartDto(20,2000);
        String addToCartJsonString = gson.toJson(addToCartDto);
        Mockito.when(cartService.addToCart(any(),any())).thenReturn(response);
        MvcResult result = this.mockMvc.perform(post("/book")
                .content(addToCartJsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .headers(httpHeaders)).andReturn();
        Assert.assertEquals(201,result.getResponse().getStatus());
    }

    @Test
    void whenUseTokenIsValid_ShouldReturnAllCartBook() throws Exception {
        List<Book> bookList=new ArrayList<>();
        Book book=new Book(bookDto);
        bookList.add(book);
        Response response=new Response("Book List",200,bookList);
        Mockito.when(cartService.getCartBook(any())).thenReturn(response);
        MvcResult result = this.mockMvc.perform(get("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .headers(httpHeaders)).andReturn();
        Assert.assertEquals(response.message,new Gson().fromJson(result.getResponse().getContentAsString(),Response.class).message);

    }

    @Test
    void whenUserToken_ShouldPlacedOrderAndReturn() throws Exception {
        Response response=new Response("Order Is Placed",200,"");
        Mockito.when(cartService.updateCart(any())).thenReturn(response);
        MvcResult result = this.mockMvc.perform(put("/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .headers(httpHeaders)).andReturn();
        Assert.assertEquals(response.message,new Gson().fromJson(result.getResponse().getContentAsString(),Response.class).message);

    }
}
