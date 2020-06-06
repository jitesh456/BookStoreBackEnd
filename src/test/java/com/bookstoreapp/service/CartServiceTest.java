package com.bookstoreapp.service;

import com.bookstoreapp.dto.AddToCartDto;
import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.model.BookCart;
import com.bookstoreapp.model.Cart;
import com.bookstoreapp.model.User;
import com.bookstoreapp.repository.IBookCartRepository;
import com.bookstoreapp.repository.IBookRepository;
import com.bookstoreapp.repository.ICartRepository;
import com.bookstoreapp.repository.IUserRepository;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.CartService;
import com.bookstoreapp.util.implementation.JwtToken;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;


@SpringBootTest
public class CartServiceTest {

    @InjectMocks
    CartService cartService;

    AddToCartDto addToCartDto;

    String token;

    @Mock
    JwtToken jwtToken;

    @Mock
    ICartRepository cartRepository;

    @Mock
    IBookRepository bookRepository;

    @Mock
    IBookCartRepository bookCartRepository;

    @Mock
    IUserRepository userRepository;

    UserRegistrationDto userRegistrationDto;
    Set<BookCart> bookCartSet;

    @BeforeEach
    void setUp() {
        addToCartDto=new AddToCartDto(12,2);
        token ="asbfj45";
        userRegistrationDto = new UserRegistrationDto("AkhilSharma", "akhil234@gmail.com",
                "Luffy456@", "8943725498");
        bookCartSet=new HashSet<>();
    }

    @Test
    void givenCartDetails_WhenProper_ReturnProperMessage() {
        BookDto bookDto1 =new BookDto("Naruto",200.0,
                20,"makashi kissimoto","Manga",
                "12345678","Advanture","story about ninja boy ");
        Book book =new Book(bookDto1);
        User user=new User(userRegistrationDto);
        book.id=13;
        Cart cart=new Cart(LocalDateTime.now(),200,false,12);
        cart.id=12;
        BookCart bookCart=new BookCart(book,cart,12);
        Mockito.when(jwtToken.validateToken(anyString())).thenReturn(true);
        Mockito.when(jwtToken.getUserId()).thenReturn(12);
        Mockito.when(userRepository.findUserById(anyInt())).thenReturn(java.util.Optional.of(user));
        Mockito.when(bookRepository.findById(anyInt())).thenReturn(java.util.Optional.of(book));
        Mockito.when(cartRepository.save(any())).thenReturn(cart);
        Mockito.when(bookCartRepository.save(any())).thenReturn(bookCart);


        Response response = cartService.addToCart(addToCartDto, token);
        Assert.assertEquals("Added to Cart",response.message);

    }
}
