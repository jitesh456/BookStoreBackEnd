
package com.bookstoreapp.service;


import com.bookstoreapp.dto.UserDetailDto;
import com.bookstoreapp.dto.UserLoginDto;
import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.model.User;
import com.bookstoreapp.model.UserDetail;
import com.bookstoreapp.repository.IUserDetailRepository;
import com.bookstoreapp.repository.IUserRepository;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.UserService;
import com.bookstoreapp.util.IJwtToken;
import com.bookstoreapp.util.implementation.JwtToken;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.*;

@SpringBootTest

public class UserServiceTest {


    @Mock
    IUserRepository userRepository;

    @Mock
    IUserDetailRepository userDetailRepository;

    UserRegistrationDto userRegistrationDto;

    String token;

    @BeforeEach
    void setUp() {
        userRegistrationDto=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                "Luffy456@","8943725498");
        token="asdgj@123";
    }


    @InjectMocks
    UserService userService;

    @Mock
    JwtToken jwtToken;

    @Test
    void givenUserDetails_WhenProper_ShouldReturnTrue() {
        userRegistrationDto=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                "Ak@1234Sh","8943725498");
        User user=new User(userRegistrationDto);
        Mockito.when(userRepository.save(any())).thenReturn(user);
        Response expectedResult = userService.addUser(userRegistrationDto);
        Assert.assertEquals("User Registered Successfully",expectedResult.body);
    }


    @Test
    void givenLoginDetails_WhenProper_ShouldReturnTrue() {
        UserLoginDto userLoginDto =new UserLoginDto("luffy@gmail.com","Luffy456@");
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        userRegistrationDto.password=bCryptPasswordEncoder.encode(userLoginDto.password);
        User user=new User(userRegistrationDto);
        Mockito.when(userRepository.findUserByEmail(any())).thenReturn(java.util.Optional.of(user));
        Mockito.when(jwtToken.doGenerateToken(anyInt())).thenReturn(token);
        String expectedResult = userService.loginUser(userLoginDto);
        Assert.assertEquals(token,expectedResult);
    }

    @Test
    void givenUserDetail_WhenProper_ShouldReturnTrue() {
        UserLoginDto userLoginDto =new UserLoginDto("luffy@gmail.com","Luffy456@");
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        userRegistrationDto.password=bCryptPasswordEncoder.encode(userLoginDto.password);
        User user=new User(userRegistrationDto);
        UserDetailDto userDetailDto=new UserDetailDto("Home","435672","101 B Street",
                "101 B Street Lucknow U.P","Lucknow","India");
        UserDetail userDetail=new UserDetail(userDetailDto);
        Mockito.when(userRepository.findUserById(anyInt())).thenReturn(java.util.Optional.of(user));
        Mockito.when(userDetailRepository.save(any())).thenReturn(userDetail);
        Mockito.when(jwtToken.validateToken(anyString())).thenReturn(true);
        Mockito.when(jwtToken.getUserId()).thenReturn(1);
        Response response = userService.userDetail(userDetailDto,token);
        Assert.assertEquals("Added User Detail Successfully",response.message);
        Assert.assertEquals(200,response.statusCode);
    }

}


