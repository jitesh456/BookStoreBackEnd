package com.bookstoreapp.service;


import com.bookstoreapp.dto.UserDetailDto;
import com.bookstoreapp.dto.UserLoginDto;
import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.model.User;
import com.bookstoreapp.model.UserDetail;
import com.bookstoreapp.repository.IUserDetailRepository;
import com.bookstoreapp.repository.IUserRepository;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.CustomerService;
import com.bookstoreapp.util.implementation.JwtToken;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
public class CustomerServiceTest {

    @Mock
    IUserRepository userRepository;

    @Mock
    IUserDetailRepository userDetailRepository;

    UserRegistrationDto userRegistrationDto;

    @Autowired
    HttpServletRequest httpServletRequest;

    String token;

    @Mock
    JwtToken jwtToken;

    @InjectMocks
    CustomerService customerService;
    User user;

    @BeforeEach
    void setUp() {
        userRegistrationDto=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                "Luffy456@","8943725498");
        token="asdgj@123";
        user=new User(userRegistrationDto);
    }



    @Test
    void givenUserDetail_WhenProper_ShouldReturnTrue() {
        UserLoginDto userLoginDto =new UserLoginDto("luffy@gmail.com","Luffy456@");
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        userRegistrationDto.password=bCryptPasswordEncoder.encode(userLoginDto.password);

        UserDetailDto userDetailDto=new UserDetailDto("Home","435672","101 B Street",
                "101 B Street Lucknow U.P","Lucknow","India");
        UserDetail userDetail=new UserDetail(userDetailDto);
        Mockito.when(userRepository.findUserById(anyInt())).thenReturn(java.util.Optional.of(user));
        Mockito.when(userDetailRepository.save(any())).thenReturn(userDetail);
        Mockito.when(jwtToken.validateToken(anyString())).thenReturn(true);
        Mockito.when(jwtToken.getUserId()).thenReturn(1);
        Response response = customerService.userDetail(userDetailDto,token);
        Assert.assertEquals("Added User Detail Successfully",response.message);
        Assert.assertEquals(200,response.statusCode);
    }

    @Test
    void whenUserFound_ShouldReturnUserDetails() {
        Mockito.when(userRepository.findUserById(anyInt())).thenReturn(java.util.Optional.of(user));
        Response response = customerService.getUserDetail(token);
        Assert.assertEquals("User Found",response.message);
        Assert.assertEquals(200,response.statusCode);

    }
}
