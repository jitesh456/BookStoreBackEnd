package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.dto.AddToCartDto;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.model.BookCart;
import com.bookstoreapp.model.Cart;
import com.bookstoreapp.repository.IBookCartRepository;
import com.bookstoreapp.repository.IBookRepository;
import com.bookstoreapp.repository.ICartRepository;
import com.bookstoreapp.repository.IUserRepository;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.ICartService;
import com.bookstoreapp.util.IJwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartService  implements ICartService {

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IBookCartRepository bookCartRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    IJwtToken jwtToken;

    @Override
    public Response addToCart(AddToCartDto addToCartDto, String token) {
        int userId=0;
        jwtToken.validateToken(token);
        userId = jwtToken.getUserId();
        Cart cart=null;
        int bookId= addToCartDto.bookId;
        int quantity= (int) addToCartDto.Quantity;
        Optional<Book >book1=bookRepository.findById(bookId);
        Book book = book1.get();
        int totalPrice= (int) (book.price*quantity);
        if(cart==null){
            cart=new Cart(LocalDateTime.now(),totalPrice,"false",quantity);
        }

        BookCart bookCart=new BookCart(book,cart,quantity);


        bookRepository.save(book);
        cartRepository.save(cart);
        bookCartRepository.save(bookCart);

        return new Response("Added to Cart",200,bookCart);

    }

}