package com.bookstoreapp.controller;

import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.UserService;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    UserService userService;

    UserRegistrationDto userRegistrationDto;

    @Autowired
    MockMvc mockMvc;


    HttpHeaders headers;

    Gson gson;

    @BeforeEach
    public void setUp() throws Exception {
        headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        gson=new Gson();
        userRegistrationDto=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                "Ak@1234Sh","8943725498");
    }

    @Test
    public void givenUserDetails_WhenProper_ShouldReturn_True() throws Exception {
        String user=new Gson().toJson(userRegistrationDto);
        Mockito.when(userService.addUser(any())).thenReturn(true);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals(200,mvcResult.getResponse().getStatus());
    }



    @Test
    public void givenUserDetails_WhenUserNameNull_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto(null,"akhil234@gmail.com",
                "Ak@1234Sh","8943725498");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any())).thenReturn(true);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("User name should not be empty", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    public void givenUserDetails_WhenUserNameNotProper_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("ab","akhil234@gmail.com",
                "Ak@1234Sh","8943725498");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any())).thenReturn(true);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("User name should start with upper case and minimum 3 character", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    public void givenUserDetails_WhenEmailNull_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("AkhilSharma",null,
                "Ak@1234Sh","8943725498");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any())).thenReturn(true);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("Email should not be null", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    public void givenUserDetails_WhenEmailNotValid_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("AkhilSharma","@abcd.com",
                "Ak@1234Sh","8943725498");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any())).thenReturn(true);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("Please enter valid email (example or example123  @gmail.com)", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }
    @Test
    public void givenUserDetails_WhenPasswordNull_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                null,"8943725498");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any())).thenReturn(true);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("Password should not be null", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    public void givenUserDetails_WhenPasswordNotValid_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                "abcd","8943725498");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any())).thenReturn(true);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("Atleast one uppercase,lowercase,number and atmost one special character", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    public void givenUserDetails_WhenPhoneNoNull_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                "Ak@1234Sh",null);
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any())).thenReturn(true);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("Mobile number should not be null", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    public void givenUserDetails_WhenPhoneNotValid_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                "Ak@1234Sh","abcd");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any())).thenReturn(true);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("Only numbers are allowed", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

}