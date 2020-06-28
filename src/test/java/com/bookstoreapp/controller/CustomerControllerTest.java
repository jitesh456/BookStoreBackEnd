package com.bookstoreapp.controller;


import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.FeedbackDto;
import com.bookstoreapp.dto.UserDetailDto;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.model.Feedback;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.CustomerService;
import com.bookstoreapp.util.implementation.JwtToken;
import com.bookstoreapp.util.implementation.SendMail;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = CustomerController.class)
@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @MockBean

    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    HttpHeaders httpHeaders=new HttpHeaders();

    Gson gson;

    @MockBean
    JwtToken jwtToken;

    @MockBean
    SendMail mailSender;

    String token;

    Response userRegistrationResponse;

    UserDetailDto userDetailDto;

    @BeforeEach
    public void setUp() throws Exception {
        httpHeaders.set("token","Qwebst43Y");
        gson=new Gson();

        token="asdgj@123";
        userRegistrationResponse = new Response("User Registered Successfully",
                200, "User Registered Successfully");

        userDetailDto=new UserDetailDto("Home","435672","101 B Street",
                "101 B Street Lucknow U.P","Lucknow","India");
    }


    @Test
    void givenUserDetail_WhenProper_ShouldReturnProperMessage() throws Exception {
        userDetailDto=new UserDetailDto("Home","435672","101 B Street",
                "101 B Street Lucknow U.P","Lucknow","India");
        String userDetailString = new Gson().toJson(userDetailDto);
        Mockito.when(customerService.userDetail(any(),anyString())).thenReturn(new Response("User Detail Added",200,""));
        MvcResult result = this.mockMvc.perform(post("/customerdetail").
                content(userDetailString)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andReturn();
        Assert.assertEquals("User Detail Added",
                gson.fromJson(result.getResponse().getContentAsString(),Response.class)
                        .message);
    }

    @Test
    void givenUserDetail_WhenAddressTypeNotProper_ShouldReturnProperMessage() throws Exception {
        userDetailDto=new UserDetailDto("home","435672","101 B Street",
                "101 B Street Lucknow U.P","Lucknow","India");
        String userDetailString = new Gson().toJson(userDetailDto);
        Mockito.when(customerService.userDetail(any(),anyString())).thenReturn(new Response("User Detail Added",200,""));
        MvcResult result = this.mockMvc.perform(post("/customerdetail").
                content(userDetailString)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andReturn();
        Assert.assertEquals("Address type should start with upper case",
                gson.fromJson(result.getResponse().getContentAsString(),Response.class)
                        .message);
    }

    @Test
    void givenUserDetail_WhenPincodeNotProper_ShouldReturnProperMessage() throws Exception {
        userDetailDto=new UserDetailDto("Home","43572","101 B Street",
                "101 B Street Lucknow U.P","Lucknow","India");
        String userDetailString = new Gson().toJson(userDetailDto);
        Mockito.when(customerService.userDetail(any(),anyString())).thenReturn(new Response("User Detail Added",200,""));
        MvcResult result = this.mockMvc.perform(post("/customerdetail").
                content(userDetailString)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andReturn();
        Assert.assertEquals("Pincode must be of 6 digits",
                gson.fromJson(result.getResponse().getContentAsString(),Response.class)
                        .message);
    }

    @Test
    void givenUserDetail_WhenAddressNotProper_ShouldReturnProperMessage() throws Exception {
        userDetailDto=new UserDetailDto("Home","432572","101 B Street",
                "101 B ","Lucknow","India");
        String userDetailString = new Gson().toJson(userDetailDto);
        Mockito.when(customerService.userDetail(any(),anyString())).thenReturn(new Response("User Detail Added",200,""));
        MvcResult result = this.mockMvc.perform(post("/customerdetail").
                content(userDetailString)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andReturn();
        Assert.assertEquals("Maximum length is 200 characters",
                gson.fromJson(result.getResponse().getContentAsString(),Response.class)
                        .message);
    }

    @Test
    void givenUserDetail_WhenCityNotProper_ShouldReturnProperMessage() throws Exception {
        userDetailDto=new UserDetailDto("Home","432572","101 B Street",
                "101 B Street UP ","cdte","India");
        String userDetailString = new Gson().toJson(userDetailDto);
        Mockito.when(customerService.userDetail(any(),anyString())).thenReturn(new Response("User Detail Added",200,""));
        MvcResult result = this.mockMvc.perform(post("/customerdetail").
                content(userDetailString)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andReturn();
        Assert.assertEquals("City name should start with upper case and minimum 3 character",
                gson.fromJson(result.getResponse().getContentAsString(),Response.class)
                        .message);
    }

    @Test
    void givenUserDetail_WhenCountryNotProper_ShouldReturnProperMessage() throws Exception {
        userDetailDto=new UserDetailDto("Home","432572","101 B Street",
                "101 B Street UP ","Lucknow","ind");
        String userDetailString = new Gson().toJson(userDetailDto);
        Mockito.when(customerService.userDetail(any(),anyString())).thenReturn(new Response("User Detail Added",200,""));
        MvcResult result = this.mockMvc.perform(post("/customerdetail").
                content(userDetailString)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andReturn();
        Assert.assertEquals("Country name should start with upper case and minimum 3 character",
                gson.fromJson(result.getResponse().getContentAsString(),Response.class)
                        .message);
    }

    @Test
    void givenUserToken_WhenProper_ShouldReturnProperMessage() throws Exception {
        userDetailDto=new UserDetailDto("Home","432572","101 B Street",
                "101 B Street UP ","Lucknow","");
        String userDetailString = new Gson().toJson(userDetailDto);
        Mockito.when(customerService.getUserDetail(anyString())).thenReturn(new Response("User Found",200,""));
        MvcResult result = this.mockMvc.perform(get("/customerdetail")
                .content("")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andReturn();
        Assert.assertEquals("User Found",
                gson.fromJson(result.getResponse().getContentAsString(),Response.class)
                        .message);
    }

    @Test
    void givenUserToken_WhenIdentifiedAndAddsFeedback_ShouldReturnProperMessage() throws Exception {
        FeedbackDto feedbackDto = new FeedbackDto(4, "Book is Interesting" ,"9876543210");
        String feedbackString = new Gson().toJson(feedbackDto);
        Mockito.when(customerService.addFeedback(any(),any())).
                thenReturn(new Response("Feedback Added Successfully",200,""));
        MvcResult result = this.mockMvc.perform(post("/feedback").content(feedbackString)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andReturn();
        Assert.assertEquals("Feedback Added Successfully",
                gson.fromJson(result.getResponse().getContentAsString(),Response.class)
                        .message);
    }


    @Test
    void givenIsbnNo_WhenProper_ShouldReturnAllFeedBack() throws Exception{
        BookDto bookDto = new BookDto("Secret of nagas", 2000.0,
                12, "Amish Tiwari", "comic",
                "987564236578", "sdfsfd", "Adaptation of the first of J.K. Rowling's popular " +
                "children's novels about Harry Potter, a boy who learns on his eleventh birthday that he is the orphaned son ");

        BookDto bookDto1 = new BookDto("Naruto", 200.0,
                20, "makashi kissimoto", "Manga",
                "12345678", "", "story about ninja boy ");
        Book book = new Book(bookDto);
        Book book1 = new Book(bookDto1);
        Feedback feedback = new Feedback(3, 4, "Book is Interesting",book);
        Feedback feedback1 = new Feedback(4, 3 ,"Nice Book to Read",book1);
        List<Feedback> feedbackList = new ArrayList<>();
        feedbackList.add(feedback);
        feedbackList.add(feedback1);
        Response response=new Response("Feedback Fetched",200,feedbackList);
        Mockito.when(customerService.getAllFeedback(any())).thenReturn(response);
        MvcResult result = this.mockMvc.perform(get("/feedback?isbn=")).andReturn();
        Assert.assertEquals(200, result.getResponse().getStatus());
        Assert.assertEquals("Feedback Fetched", gson.fromJson(result.getResponse().
                getContentAsString(), Response.class).message);

    }



}
