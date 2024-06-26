package com.bookstoreapp.controller;

import com.bookstoreapp.dto.AddToCartDto;
import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.response.OrderPlacedResponse;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.AdminService;
import com.bookstoreapp.service.Implementation.CartService;
import com.bookstoreapp.util.implementation.SendMail;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = CartController.class)
@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {

    @MockBean
    AdminService adminService;

    @Autowired
    MockMvc mockMvc;

    Gson gson;

    @Autowired
    CartController cartController;

    @MockBean
    CartService cartService;

    HttpHeaders httpHeaders=new HttpHeaders();

    BookDto bookDto;

    @MockBean
    SendMail mailSender;

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
    void whenUserTokenProper_ShouldPlacedOrderAndReturn() throws Exception {

        Response response=new Response("Order Is Placed",200,"");
        Mockito.when(cartService.updateCart(any())).thenReturn(response);
        MvcResult result = this.mockMvc.perform(put("/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .headers(httpHeaders)).andReturn();
        Assert.assertEquals(response.message,new Gson().fromJson(result.getResponse().getContentAsString(),Response.class).message);

    }

    @Test
    void givenBookId_WhenProper_ShouldDeleteBook() throws Exception {

        Response response=new Response("Book Is Removed From Cart",200,"");
        Mockito.when(cartService.deleteBook(anyInt(),any())).thenReturn(response);
        MvcResult result = this.mockMvc.perform(delete("/book/{id}",3)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .headers(httpHeaders)).andReturn();
        Assert.assertEquals(response.message,new Gson().fromJson(result.getResponse().getContentAsString(),Response.class).message);

    }

    @Test
    void whenUserTokenProper_ShouldReturnAllCart() throws Exception {

        List<OrderPlacedResponse> bookCartList=new ArrayList<>();
        Response response=new Response("BooK And Cart List",200,bookCartList);
        Mockito.when(cartService.orderDetails(any())).thenReturn(response);
        MvcResult result = this.mockMvc.perform(get("/order/details",3)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .headers(httpHeaders)).andReturn();
        Assert.assertEquals(response.message,new Gson().fromJson(result.getResponse().getContentAsString(),Response.class).message);
    }

}
