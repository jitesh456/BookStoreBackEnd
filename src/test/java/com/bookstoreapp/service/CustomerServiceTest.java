package com.bookstoreapp.service;

import com.bookstoreapp.dto.*;
import com.bookstoreapp.model.*;
import com.bookstoreapp.repository.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
public class CustomerServiceTest {

    @Mock
    IUserRepository userRepository;

    @Mock
    IUserDetailRepository userDetailRepository;

    UserRegistrationDto userRegistrationDto;

    @Mock
    IBookRepository bookRepository;

    @Mock
    IFeedbackRepository feedbackRepository;

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

    @Test
    void givenFeedback_WhenProper_ShouldReturnTrue(){
        FeedbackDto feedbackDto=new FeedbackDto(3,"Good Book","9765432133");
        String isbn = feedbackDto.isbn;
        BookDto bookDto = new BookDto("Secret of nagas", 2000.0,12, "Amish Tiwari", "comic",
                "998542365", "sdfsfd", "ABCD");
        Book book = new Book(bookDto);
        book.id=3;
        Feedback feedback=new Feedback(5,3,"Good Book",book);
        Mockito.when(userRepository.findUserById(anyInt())).thenReturn(java.util.Optional.of(user));
        Mockito.when(jwtToken.validateToken(anyString())).thenReturn(true);
        Mockito.when(jwtToken.getUserId()).thenReturn(1);
        Mockito.when(jwtToken.getUserId()).thenReturn(1);
        Mockito.when(bookRepository.findByIsbn(isbn)).thenReturn(Optional.of(book));
        Mockito.when(feedbackRepository.save(any())).thenReturn(feedback);
        Response response = customerService.addFeedback(token,feedbackDto);
        Assert.assertEquals(200,response.statusCode);
    }

    @Test
    void givenISBN_WhenIdentified_ShouldReturnProperMessage(){
        FeedbackDto feedbackDto=new FeedbackDto(3,"Good Book","9765432133");
        BookDto bookDto = new BookDto("Secret of nagas", 2000.0,12, "Amish Tiwari", "comic",
                "998542365", "sdfsfd", "ABCD");
        Book book = new Book(bookDto);
        book.id=1;
        List<Integer> feedbackIds=new ArrayList<>();
        Feedback feedback=new Feedback(5,3,"Good Book",book);
        feedback.id=1;
        feedbackIds.add(feedback.id);
        Mockito.when(bookRepository.findByIsbn(any())).thenReturn(Optional.of(book));
        Mockito.when(feedbackRepository.getfeedbackIds(anyInt())).thenReturn(feedbackIds);
        Mockito.when(feedbackRepository.findById(any())).thenReturn(Optional.of(feedback));
        Mockito.when(userRepository.findUserById(anyInt())).thenReturn(java.util.Optional.of(user));
        Response response = customerService.getAllFeedback(feedbackDto.isbn);
        Assert.assertEquals(200,response.statusCode);
    }

    @Test
    void givenUserToken_WhenIdentified_ShouldReturnProperMessage() {
        BookDto bookDto = new BookDto("Secret of nagas", 2000.0,12, "Amish Tiwari", "comic",
                "998542365", "sdfsfd", "ABCD");
        Book book = new Book(bookDto);
        book.id=3;
        Feedback feedback=new Feedback(5,3,"Good Book",book);
        Mockito.when(userRepository.findUserById(anyInt())).thenReturn(java.util.Optional.of(user));
        Mockito.when(jwtToken.validateToken(anyString())).thenReturn(true);
        Mockito.when(jwtToken.getUserId()).thenReturn(1);
        Mockito.when(feedbackRepository.findById(any())).thenReturn(Optional.of(feedback));
        Response response = customerService.getUserFeedback(3,token);
        Assert.assertEquals("User Feedback Fetched",response.message);
        Assert.assertEquals(200,response.statusCode);
    }

}
