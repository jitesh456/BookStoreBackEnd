package com.bookstoreapp.controller;


import com.bookstoreapp.dto.UserDetailDto;
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
        MvcResult result = this.mockMvc.perform(post("/userdetail").
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
        MvcResult result = this.mockMvc.perform(post("/userdetail").
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
        MvcResult result = this.mockMvc.perform(post("/userdetail").
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
        MvcResult result = this.mockMvc.perform(post("/userdetail").
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
        MvcResult result = this.mockMvc.perform(post("/userdetail").
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
        MvcResult result = this.mockMvc.perform(post("/userdetail").
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
        MvcResult result = this.mockMvc.perform(get("/fetchdetail")
                .content("")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andReturn();
        Assert.assertEquals("User Found",
                gson.fromJson(result.getResponse().getContentAsString(),Response.class)
                        .message);
    }

}