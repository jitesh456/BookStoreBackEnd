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
import java.util.*;

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

        Cart userCart = null;
        User user = validate(token).get();
        Optional<Cart> cart = user.carts.stream().filter(cart1 -> !cart1.placedOrder).findFirst();
        int bookId = addToCartDto.bookId;
        Optional<Book> book1 = bookRepository.findById(bookId);
        Book book = book1.get();
        int quantity = (int) addToCartDto.quantity;

        int totalPrice = (int) (book.price * quantity);
        if (cart.isPresent()) {
            userCart = cart.get();
            BookCart bookCart = new BookCart(book, userCart, quantity);
            userCart.quantity = userCart.quantity + quantity;
            userCart.totalPrice = userCart.totalPrice + totalPrice;
            bookCartRepository.save(bookCart);
            userCart.bookCartList.add(bookCart);
            cartRepository.save(userCart);
        }
        if (!cart.isPresent()) {
            userCart = new Cart(LocalDateTime.now(), totalPrice, false, quantity);
            cartRepository.save(userCart);
            user.carts.add(userCart);
            userRepository.save(user);
            BookCart bookCart = new BookCart(book, userCart, quantity);
            bookCartRepository.save(bookCart);
            userCart.bookCartList.add(bookCart);
            cartRepository.save(userCart);
        }
        return new Response("Added to Cart", 200, "Book Is Added To Cart");
    }

    @Override
    public Response getCartBook(String token) {

        Optional<User> user =validate(token);
        List<Cart> userCart = user.map(user1 -> user1.carts).orElse(null);
        Optional<Cart> cart = userCart.stream().filter(cart1 -> !cart1.placedOrder).findAny();
        List<BookCart> bookCartList = cart.map(value -> value.bookCartList).orElse(Collections.emptyList());
        List<Book> bookList = new ArrayList<>();
        bookCartList.forEach(bookCart -> {

            bookList.add(bookRepository.
                    findById(bookCart.bookCartID.book_Id).get());
        });
        return new Response("BookList", 200, bookList);

    }

    @Override
    public Response updateCart(String token) {

        Optional<User> user = validate(token);
        List<Cart> userCart = user.map(user1 -> user.get().carts).orElse(null);
        Cart cart = userCart.stream().filter(cart1 -> !cart1.placedOrder).findAny().get();
        cart.placedOrder = true;
        cart.orderPlacedDate = LocalDateTime.now();
        cartRepository.save(cart);
        return new Response("Order Placed Successfully", 200, "");

    }

    @Override
    public Response deleteBook(int id, String token) {

        Optional<User> user = validate(token);
        Cart cart = user.isPresent() ? user.get().carts.stream().filter(cart1 -> !cart1.placedOrder).findAny().get() : null;
        List<BookCart> bookCart = new ArrayList<>();
        int quantity = cart.quantity - bookCartRepository.getBookCartQuantity(id, cart.id);
        bookCartRepository.updateBookCart(id, cart.id);
        cart.quantity = quantity;
        cartRepository.save(cart);
        return new Response("BookList", 200, "");
    }

    @Override
    public Response orderDetails(String token) {
        Optional<User> user = validate(token);
        List<Cart> userCart = user.map(user1 -> user.get().carts).orElse(null);
        Iterator<Cart> cartIterator = userCart.stream().filter(cart1 -> cart1.placedOrder).iterator();

        List<OrderPlacedResponse> bookCartList = new ArrayList<>();
        while (cartIterator.hasNext()) {
            Cart cart = cartIterator.next();
            List<Book> bookList = new ArrayList<>();
            cart.bookCartList.stream().forEach(x -> bookList.add(bookRepository.findById(x.bookCartID.book_Id).get()));
            bookCartList.add(new OrderPlacedResponse(bookList, cart));
        }
        return new Response("Book Cart List", 200, bookCartList);

    }

    private Optional<User> validate(String token){
        jwtToken.validateToken(token);
        int userId = jwtToken.getUserId();
        return userRepository.findUserById(userId);
    }

}