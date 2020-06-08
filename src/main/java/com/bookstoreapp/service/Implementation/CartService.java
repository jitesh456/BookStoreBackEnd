package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.dto.AddToCartDto;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.model.BookCart;
import com.bookstoreapp.model.Cart;
import com.bookstoreapp.model.User;
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
import java.util.ArrayList;
import java.util.Collections;
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
        Cart userCart=null;
        User user = userRepository.findUserById(userId).get();
        Optional<Cart>  cart = user.getCarts().stream().filter(cart1 -> !cart1.placedOrder).findFirst();
        int bookId= addToCartDto.bookId;
        Optional<Book >book1=bookRepository.findById(bookId);
        Book book = book1.get();
        int quantity= (int) addToCartDto.quantity;

        int totalPrice= (int) (book.price*quantity);
        if(cart.isPresent()) {
            userCart=cart.get();
            BookCart bookCart=new BookCart(book,userCart,quantity);
            userCart.quantity=userCart.quantity+quantity;
            userCart.totalPrice=userCart.totalPrice+totalPrice;
            bookCartRepository.save(bookCart);
            userCart.bookCartList.add(bookCart);
            cartRepository.save(userCart);
        }
        if(!cart.isPresent())
        {
            userCart=new Cart(LocalDateTime.now(),totalPrice,false,quantity);
            cartRepository.save(userCart);
            user.setCarts(userCart);
            userRepository.save(user);
            BookCart bookCart=new BookCart(book,userCart,quantity);
            bookCartRepository.save(bookCart);
            userCart.bookCartList.add(bookCart);
            cartRepository.save(userCart);
        }
        return new Response("Added to Cart",200,"Book Is Added To Cart");
    }

    @Override
    public Response getCartBook(String token) {
        jwtToken.validateToken(token);
        int userId = jwtToken.getUserId();
        Optional<User> user = userRepository.findUserById(userId);
        List<Cart> userCart = user.map(User::getCarts).orElse(null);
        Optional<Cart> cart= userCart.stream().filter(cart1 -> !cart1.placedOrder).findAny();
        List<BookCart> bookCartList = cart.map(value -> value.bookCartList).orElse(Collections.emptyList());
        List<Book> bookList=new ArrayList<>();
        bookCartList.forEach(bookCart -> {

            bookList.add( bookRepository.
                    findById(bookCart.bookCartID.book_Id).get());
        });
        return new Response("BookList",200,bookList);

    }

    @Override
    public Response updateCart(String token) {
        return null;
    }


}