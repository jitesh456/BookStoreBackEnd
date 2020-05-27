package com.bookstoreapp.controller;

import com.bookstoreapp.dto.UserRegistrationDto;
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
}