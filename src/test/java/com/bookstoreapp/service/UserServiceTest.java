
package com.bookstoreapp.service;


import com.bookstoreapp.dto.UserLoginDto;
import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.model.User;
import com.bookstoreapp.repository.IUserRepository;
import com.bookstoreapp.service.Implementation.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest

public class UserServiceTest {


    @Mock
    IUserRepository userRepository;

    UserRegistrationDto userRegistrationDto;

    @BeforeEach
    void setUp() {
        userRegistrationDto=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                "Luffy456@","8943725498");
    }


    @InjectMocks
    UserService userService;

    @Test
    void givenUserDetails_WhenProper_ShouldReturnTrue() {
        userRegistrationDto=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                "Ak@1234Sh","8943725498");
        User user=new User(userRegistrationDto);
        Mockito.when(userRepository.save(any())).thenReturn(user);
        boolean expectedResult = userService.addUser(userRegistrationDto);
        Assert.assertEquals(true,expectedResult);
    }

    @Test
    void givenLoginDetails_WhenProper_ShouldReturnTrue() {
        UserLoginDto userLoginDto =new UserLoginDto("luffy@gmail.com","Luffy456@");

        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

        userRegistrationDto.password=bCryptPasswordEncoder.encode(userLoginDto.password);
        User user=new User(userRegistrationDto);
        Mockito.when(userRepository.findUserByEmail(any())).thenReturn(java.util.Optional.of(user));
        boolean expectedResult = userService.loginUser(userLoginDto);
        Assert.assertEquals(true,expectedResult);
    }

}


