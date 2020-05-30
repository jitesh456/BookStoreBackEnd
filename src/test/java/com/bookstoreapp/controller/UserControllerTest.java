package com.bookstoreapp.controller;

import com.bookstoreapp.dto.UserLoginDto;
import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.enums.LoginResponseMessage;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
    Response loginResponse;

    @BeforeEach
    public void setUp() throws Exception {
        headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        gson=new Gson();
        userRegistrationDto=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                "Ak@1234Sh","8943725498");
        loginResponse= new Response(LoginResponseMessage.RESPONSE_MESSAGE.responseMessage(true), 200, true);
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
        Assert.assertEquals("User name should start with upper case and minimum 3 character", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    public void givenUserDetails_WhenUserNameEmpty_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("","akhil234@gmail.com",
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
        Assert.assertEquals("Email should not be Empty", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }


    @Test
    public void givenUserDetails_WhenEmailEmpty_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("AkhilSharma",null,
                "Ak@1234Sh","8943725498");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any())).thenReturn(true);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("Email should not be Empty", gson.fromJson(mvcResult.getResponse()
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
        Assert.assertEquals("Please enter valid email", gson.fromJson(mvcResult.getResponse()
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
        Assert.assertEquals("Atleast one uppercase,lowercase,number and atmost one special character with minimum length 8", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    public void givenUserDetails_WhenPasswordEmpty_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                null,"8943725498");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any())).thenReturn(true);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("Atleast one uppercase,lowercase,number and atmost one special character with minimum length 8", gson.fromJson(mvcResult.getResponse()
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
        Assert.assertEquals("Atleast one uppercase,lowercase,number and atmost one special character with minimum length 8", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    public void givenUserDetails_WhenPhoneNull_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                "Ak@1234Sh",null);
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any())).thenReturn(true);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("Mobile No Only have 10 Digit", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    public void givenUserDetails_WhenPhoneEmpty_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                "Ak@1234Sh","");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any())).thenReturn(true);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("Mobile No Only have 10 Digit", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    public void givenUserDetails_WhenPhoneNoLessThenTenDigit_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                "Ak@1234Sh","985642356");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any())).thenReturn(true);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("Mobile No Only have 10 Digit", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    void givenUserLoginDetails_WhenProper_returnTrue() throws Exception {
        UserLoginDto userLoginDto=new UserLoginDto("Luffy@gmail.com","Luffy@123");
        String userLoginDtoString = new Gson().toJson(userLoginDto);
        Mockito.when(userService.loginUser(any())).thenReturn(loginResponse);
        MvcResult result = this.mockMvc.perform(post("/login").
                content(userLoginDtoString).
                contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void givenUserLoginDetails_WhenEmailNull_returnTrue() throws Exception {
        UserLoginDto userLoginDto=new UserLoginDto(null,"Luffy@123");
        String userLoginDtoString = new Gson().toJson(userLoginDto);
        Mockito.when(userService.loginUser(any())).thenReturn(loginResponse);
        MvcResult result = this.mockMvc.perform(post("/login").
                content(userLoginDtoString).
                contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals("Please enter valid email", gson.fromJson(result.getResponse().getContentAsString(),Response.class).message);
    }


    @Test
    void givenUserLoginDetails_WhenEmailEmpty_returnTrue() throws Exception {
        UserLoginDto userLoginDto=new UserLoginDto("","Luffy@123");
        String userLoginDtoString = new Gson().toJson(userLoginDto);
        Mockito.when(userService.loginUser(any())).thenReturn(loginResponse);
        MvcResult result = this.mockMvc.perform(post("/login").
                content(userLoginDtoString).
                contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals("Please enter valid email", gson.fromJson(result.getResponse().getContentAsString(),Response.class).message);
    }

    @Test
    void givenUserLoginDetails_WhenEmailWrong_returnTrue() throws Exception {
        UserLoginDto userLoginDto=new UserLoginDto("abc123","Jitesh@123");
        String userLoginDtoString = new Gson().toJson(userLoginDto);
        Mockito.when(userService.loginUser(any())).thenReturn(loginResponse);
        MvcResult result = this.mockMvc.perform(post("/login").
                content(userLoginDtoString).
                contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals("Please enter valid email", gson.fromJson(result.getResponse().getContentAsString(),Response.class).message);
    }

    @Test
    void givenUserLoginDetails_WhenPasswordWrong_returnTrue() throws Exception {
        UserLoginDto userLoginDto=new UserLoginDto("Luffy@gmail.com","luffy@123");
        String userLoginDtoString = new Gson().toJson(userLoginDto);
        Mockito.when(userService.loginUser(any())).thenReturn(loginResponse);
        MvcResult result = this.mockMvc.perform(post("/login").
                content(userLoginDtoString).
                contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals("Atleast one uppercase,lowercase,number and " +
                "atmost one special character with minimum length 8", gson.fromJson(result.getResponse().getContentAsString(),Response.class).message);
    }

    @Test
    void givenUserLoginDetails_WhenPasswordNull_returnTrue() throws Exception {
        UserLoginDto userLoginDto=new UserLoginDto("Luffy@gmail.com",null);
        String userLoginDtoString = new Gson().toJson(userLoginDto);
        Mockito.when(userService.loginUser(any())).thenReturn(loginResponse);
        MvcResult result = this.mockMvc.perform(post("/login").
                content(userLoginDtoString).
                contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals("Atleast one uppercase,lowercase,number and " +
                        "atmost one special character with minimum length 8",
                gson.fromJson(result.getResponse().getContentAsString(),Response.class)
                        .message);
    }


    @Test
    void givenUserLoginDetails_WhenPasswordEmpty_returnTrue() throws Exception {
        UserLoginDto userLoginDto=new UserLoginDto("Luffy@gmail.com","");
        String userLoginDtoString = new Gson().toJson(userLoginDto);
        Mockito.when(userService.loginUser(any())).thenReturn(loginResponse);
        MvcResult result = this.mockMvc.perform(post("/login").
                content(userLoginDtoString).
                contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals("Atleast one uppercase,lowercase,number and atmost " +
                        "one special character with minimum length 8",
                gson.fromJson(result.getResponse().getContentAsString(),Response.class)
                        .message);
    }



}