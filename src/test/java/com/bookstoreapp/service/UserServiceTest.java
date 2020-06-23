
package com.bookstoreapp.service;

import com.bookstoreapp.dto.NotificationDto;
import com.bookstoreapp.dto.UserLoginDto;
import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.exception.UserException;
import com.bookstoreapp.model.User;
import com.bookstoreapp.repository.IUserDetailRepository;
import com.bookstoreapp.repository.IUserRepository;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.UserService;
import com.bookstoreapp.util.IResetPasswordTemplate;
import com.bookstoreapp.util.IVerifyEmailTemplate;
import com.bookstoreapp.util.implementation.JwtToken;
import com.bookstoreapp.util.implementation.SendMail;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest

public class UserServiceTest {


    @Mock
    IUserRepository userRepository;

    @Mock
    IResetPasswordTemplate iResetPasswordTemplate;
    @Mock
    IUserDetailRepository userDetailRepository;

    @Mock
    IVerifyEmailTemplate iVerifyEmailTemplate;

    UserRegistrationDto userRegistrationDto;

    @Autowired
    HttpServletRequest httpServletRequest;

    String token;


    @BeforeEach
    void setUp() {
        userRegistrationDto = new UserRegistrationDto("AkhilSharma", "akhil234@gmail.com",
                "Luffy456@", "8943725498");
        token = "asdgj@123";
    }


    @InjectMocks
    UserService userService;


    @Mock
    SendMail mailSender;

    @Mock
    JwtToken jwtToken;

    @Test
    void givenUserDetails_WhenProper_ShouldReturnTrue() throws MessagingException {

        userRegistrationDto = new UserRegistrationDto("AkhilSharma", "akhil234@gmail.com",
                "Ak@1234Sh", "8943725498");
        NotificationDto notificationDto = new NotificationDto("ashish@gmail.com", "Activate account", "");
        User user = new User(userRegistrationDto);
        Mockito.when(userRepository.save(any())).thenReturn(user);
        Mockito.when(iVerifyEmailTemplate.verifyEmailTemplate(any())).thenReturn("UrlCreated");
        Mockito.when(mailSender.sendMail(any())).thenReturn("Mail sent successfully");
        Response expectedResult = userService.addUser(userRegistrationDto, httpServletRequest);
        Assert.assertEquals("User Registered Successfully", expectedResult.body);
    }


    @Test
    void givenLoginDetails_WhenProper_ShouldReturnTrue() {

        UserLoginDto userLoginDto = new UserLoginDto("luffy@gmail.com", "Luffy456@");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userRegistrationDto.password = bCryptPasswordEncoder.encode(userLoginDto.password);
        User user = new User(userRegistrationDto);
        Mockito.when(userRepository.findUserByEmail(any())).thenReturn(java.util.Optional.of(user));
        Mockito.when(jwtToken.generateToken(anyInt())).thenReturn(token);
        String expectedResult = userService.loginUser(userLoginDto);
        Assert.assertEquals(token, expectedResult);
    }

    @Test
    void givenEmailandServletRequest_WhenProper_shouldReturnSuccessResponse() throws MessagingException {

        userRegistrationDto = new UserRegistrationDto("AkhilSharma", "akhil234@gmail.com",
                "Ak@1234Sh", "8943725498");
        User user = new User(userRegistrationDto);
        Mockito.when(userRepository.findUserByEmail(any())).thenReturn(java.util.Optional.of(user));
        Mockito.when(mailSender.sendMail(any())).thenReturn("Mail sent successfully");
        Mockito.when(iResetPasswordTemplate.getPasswordTemplate(any())).thenReturn("");
        Response response = userService.forgetPassword("akhil234@gmail.com", httpServletRequest);
        Assert.assertEquals(200, response.statusCode);
    }

    @Test
    void givenEmailandServletRequest_WhenImProper_shouldThrowException() throws MessagingException {
        try {
            User user = new User(userRegistrationDto);
            Optional<User> userdata = java.util.Optional.of(user);
            Mockito.when(userRepository.findUserByEmail(any())).thenReturn(userdata);
            Response response = userService.forgetPassword("akhil234@gmail.com", httpServletRequest);
            System.out.println("gfvnjdsnvjk");
        } catch (UserException e) {
            System.out.println(e.exceptionType);
            Assert.assertEquals(UserException.ExceptionType.USER_NOT_FOUND, e.exceptionType);
        }
    }


    @Test
    void givenPassword_WhenUserNotExit_shouldThrowException() throws MessagingException {

        try {
            int userid = 2;
            User user = new User(userRegistrationDto);
            Optional<User> userdata = java.util.Optional.of(user);
            User user1 = new User(userRegistrationDto);
            Mockito.when(jwtToken.getUserId()).thenReturn(userid);
            Mockito.when(userRepository.save(any())).thenReturn(user1);
            Response response = userService.resetPassword(token, "Abcd@1234");
        } catch (UserException e) {
            System.out.println(e.exceptionType);
            Assert.assertEquals(UserException.ExceptionType.USER_NOT_FOUND, e.exceptionType);
        }
    }


}


