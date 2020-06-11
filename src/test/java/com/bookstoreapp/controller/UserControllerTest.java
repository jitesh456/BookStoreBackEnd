package com.bookstoreapp.controller;

import com.bookstoreapp.dto.UserDetailDto;
import com.bookstoreapp.dto.UserLoginDto;
import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.UserService;
import com.bookstoreapp.util.implementation.JwtToken;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers =UserController.class)
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @MockBean
    UserService userService;

    UserRegistrationDto userRegistrationDto;

    UserDetailDto userDetailDto;

    @Autowired
    MockMvc mockMvc;

    HttpHeaders httpHeaders=new HttpHeaders();

    Gson gson;


    @MockBean
    JwtToken jwtToken;

    String token;

    Response userRegistrationResponse;

    @BeforeEach
    public void setUp() throws Exception {
        httpHeaders.set("token","Qwebst43Y");
        gson=new Gson();
        userRegistrationDto=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                "Ak@1234Sh","8943725498");
        token="asdgj@123";
        userRegistrationResponse = new Response("User Registered Successfully",
                200, "User Registered Successfully");

        userDetailDto=new UserDetailDto("Home","435672","101 B Street",
                "101 B Street Lucknow U.P","Lucknow","India");
    }

    @Test
    public void givenUserDetails_WhenProper_ShouldReturn_properMessage() throws Exception {
        String user=new Gson().toJson(userRegistrationDto);
        Mockito.when(userService.addUser(any(),any())).thenReturn(userRegistrationResponse);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals(200,mvcResult.getResponse().getStatus());
    }



    @Test
    public void givenUserDetails_WhenUserNameNull_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("","akhil234@gmail.com",
                "Ak@1234Sh","8943725498");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any(),any())).thenReturn(userRegistrationResponse);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        Assert.assertEquals("User name should start with upper case and minimum 3 character", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    public void givenUserDetails_WhenUserNameEmpty_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("","akhil234@gmail.com",
                "Ak@1234Sh","8943725498");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any(),any())).thenReturn(userRegistrationResponse);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("User name should start with upper case and minimum 3 character", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    public void givenUserDetails_WhenUserNameNotProper_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("ab","akhil234@gmail.com",
                "Ak@1234Sh","8943725498");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any(),any())).thenReturn(userRegistrationResponse);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("User name should start with upper case and minimum 3 character", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    public void givenUserDetails_WhenEmailNull_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("AkhilSharma",null,
                "Ak@1234Sh","8943725498");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any(),any())).thenReturn(userRegistrationResponse);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("Email should not be Empty", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }


    @Test
    public void givenUserDetails_WhenEmailEmpty_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("AkhilSharma",null,
                "Ak@1234Sh","8943725498");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any(),any())).thenReturn(userRegistrationResponse);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("Email should not be Empty", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }



    @Test
    public void givenUserDetails_WhenEmailNotValid_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("AkhilSharma","@abcd.com",
                "Ak@1234Sh","8943725498");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any(),any())).thenReturn(userRegistrationResponse);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("Please enter valid email", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }


    @Test
    public void givenUserDetails_WhenPasswordNull_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                null,"8943725498");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any(),any())).thenReturn(userRegistrationResponse);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("Atleast one uppercase,lowercase,number and atmost one special character with minimum length 8", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    public void givenUserDetails_WhenPasswordEmpty_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                null,"8943725498");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any(),any())).thenReturn(userRegistrationResponse);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("Atleast one uppercase,lowercase,number and atmost one special character with minimum length 8", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    public void givenUserDetails_WhenPasswordNotValid_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                "abcd","8943725498");
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any(),any())).thenReturn(userRegistrationResponse);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("Atleast one uppercase,lowercase,number and atmost one special character with minimum length 8", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    public void givenUserDetails_WhenPhoneNull_ShouldReturn_properErrorMessage() throws Exception {
        UserRegistrationDto userRegistrationDto1=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                "Ak@1234Sh",null);
        String user=new Gson().toJson(userRegistrationDto1);
        Mockito.when(userService.addUser(any(),any())).thenReturn(userRegistrationResponse);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .characterEncoding("utf-8")
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
        Mockito.when(userService.addUser(any(),any())).thenReturn(userRegistrationResponse);
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

        Mockito.when(userService.addUser(any(),any())).thenReturn(userRegistrationResponse);
        MvcResult mvcResult=this.mockMvc.perform(post("/user")
                .content(user)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("Mobile No Only have 10 Digit", gson.fromJson(mvcResult.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    void givenUserLoginDetails_WhenProper_ShouldReturnProperMessage() throws Exception {
        UserLoginDto userLoginDto=new UserLoginDto("Luffy@gmail.com","Luffy@123");
        String userLoginDtoString = new Gson().toJson(userLoginDto);
        Mockito.when(userService.loginUser(any())).thenReturn(token);
        MvcResult result = this.mockMvc.perform(post("/login").
                content(userLoginDtoString).
                contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void givenUserLoginDetails_WhenEmailNull_ShouldReturnProperMessage() throws Exception {
        UserLoginDto userLoginDto=new UserLoginDto(null,"Luffy@123");
        String userLoginDtoString = new Gson().toJson(userLoginDto);
        Mockito.when(userService.loginUser(any())).thenReturn(token);
        MvcResult result = this.mockMvc.perform(post("/login").
                content(userLoginDtoString).
                contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals("Please enter valid email", gson.fromJson(result.getResponse().getContentAsString(),Response.class).message);
    }


    @Test
    void givenUserLoginDetails_WhenEmailEmpty_ShouldReturnProperMessage() throws Exception {
        UserLoginDto userLoginDto=new UserLoginDto("","Luffy@123");
        String userLoginDtoString = new Gson().toJson(userLoginDto);
        Mockito.when(userService.loginUser(any())).thenReturn(token);
        MvcResult result = this.mockMvc.perform(post("/login").
                content(userLoginDtoString).
                contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals("Please enter valid email", gson.fromJson(result.getResponse().getContentAsString(),Response.class).message);
    }

    @Test
    void givenUserLoginDetails_WhenEmailWrong_ShouldReturnProperMessage() throws Exception {
        UserLoginDto userLoginDto=new UserLoginDto("abc123","Jitesh@123");
        String userLoginDtoString = new Gson().toJson(userLoginDto);
        Mockito.when(userService.loginUser(any())).thenReturn(token);
        MvcResult result = this.mockMvc.perform(post("/login").
                content(userLoginDtoString).
                contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals("Please enter valid email", gson.fromJson(result.getResponse().getContentAsString(),Response.class).message);
    }

    @Test
    void givenUserLoginDetails_WhenPasswordWrong_ShouldReturnProperMessage() throws Exception {
        UserLoginDto userLoginDto=new UserLoginDto("Luffy@gmail.com","luffy@123");
        String userLoginDtoString = new Gson().toJson(userLoginDto);
        Mockito.when(userService.loginUser(any())).thenReturn(token);
        MvcResult result = this.mockMvc.perform(post("/login").
                content(userLoginDtoString).
                contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals("Atleast one uppercase,lowercase,number and " +
                "atmost one special character with minimum length 8", gson.fromJson(result.getResponse().getContentAsString(),Response.class).message);
    }

    @Test
    void givenUserLoginDetails_WhenPasswordNull_ShouldReturnProperMessage() throws Exception {
        UserLoginDto userLoginDto=new UserLoginDto("Luffy@gmail.com",null);
        String userLoginDtoString = new Gson().toJson(userLoginDto);
        Mockito.when(userService.loginUser(any())).thenReturn(token);
        MvcResult result = this.mockMvc.perform(post("/login").
                content(userLoginDtoString).
                contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals("Atleast one uppercase,lowercase,number and " +
                        "atmost one special character with minimum length 8",
                gson.fromJson(result.getResponse().getContentAsString(),Response.class)
                        .message);
    }


    @Test
    void givenUserLoginDetails_WhenPasswordEmpty_ShouldReturnProperMessage() throws Exception {
        UserLoginDto userLoginDto=new UserLoginDto("Luffy@gmail.com","");
        String userLoginDtoString = new Gson().toJson(userLoginDto);
        Mockito.when(userService.loginUser(any())).thenReturn(token);
        MvcResult result = this.mockMvc.perform(post("/login").
                content(userLoginDtoString).
                contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals("Atleast one uppercase,lowercase,number and atmost " +
                        "one special character with minimum length 8",
                gson.fromJson(result.getResponse().getContentAsString(),Response.class)
                        .message);
    }

    @Test
    void givenEmailId_WhenFound_ShouldReturnProperMessage() throws Exception{
        String emailId= "abcd@gmail.com";
        String email= gson.toJson(emailId);
        Mockito.when(userService.forgetPassword(any(),any())).thenReturn(new Response("Email Successfull",200,""));
        MvcResult result = this.mockMvc.perform(get("/forget?email="))
                .andReturn();
        Assert.assertEquals(200, gson.fromJson(result.getResponse().getContentAsString(),Response.class)
                .statusCode);
    }



    @Test
    void givenPassword_WhenProper_ShouldReturnProperMessage() throws Exception{
        String password= "Abcd@1234";
        Mockito.when(userService.resetPassword(any(),any())).thenReturn(new Response("Password Reset Successfull",200,""));
        MvcResult result = this.mockMvc.perform(get("/reset/password?password=")
                .characterEncoding("utf-8")
                .headers(httpHeaders))
                .andReturn();
        Assert.assertEquals(200, gson.fromJson(result.getResponse().getContentAsString(),Response.class)
                .statusCode);
    }


}