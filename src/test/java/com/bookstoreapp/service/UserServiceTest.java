
package com.bookstoreapp.service;


import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.model.User;
import com.bookstoreapp.repository.IUserRepository;
import com.bookstoreapp.service.Implementation.UserService;
import org.junit.Assert;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class UserServiceTest {

    @Mock
    IUserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    void givenUserDetails_WhenProper_ShouldReturnTrue() {
        UserRegistrationDto userRegistrationDto=new UserRegistrationDto("AkhilSharma","akhil234@gmail.com",
                "Ak@1234Sh","8943725498");
        User user=new User(userRegistrationDto);
        Mockito.when(userRepository.save(any())).thenReturn(java.util.Optional.of(user));
        boolean expectedResult = userService.addUser(userRegistrationDto);
        Assert.assertEquals(true,expectedResult);
    }

}


