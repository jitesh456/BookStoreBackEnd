package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.dto.AddToCartDto;
import com.bookstoreapp.dto.NotificationDto;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.model.BookCart;
import com.bookstoreapp.model.Cart;
import com.bookstoreapp.model.User;
import com.bookstoreapp.repository.IBookCartRepository;
import com.bookstoreapp.repository.IBookRepository;
import com.bookstoreapp.repository.ICartRepository;
import com.bookstoreapp.repository.IUserRepository;
import com.bookstoreapp.response.OrderPlacedResponse;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.ICartService;
import com.bookstoreapp.util.IJwtToken;
import com.bookstoreapp.util.IOrderPlaceTemplet;
import com.bookstoreapp.util.ISendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    ISendMail sendMail;

    @Autowired
    IOrderPlaceTemplet orderPlaceTemplet;

    @Override
    public Response addToCart(AddToCartDto addToCartDto, String token) {

        Cart userCart=null;
        User user = validate(token).get();
        Optional<Cart> cart = user.carts.stream().filter(cart1 -> !cart1.placedOrder).findFirst();
        int bookId = addToCartDto.bookId;
        Optional<Book> book1 = bookRepository.findById(bookId);
        Book book = book1.get();
        int quantity =addToCartDto.quantity;


        if (cart.isPresent()) {
            userCart = cart.get();
            List<BookCart> bookCarts = userCart.bookCartList.stream()
                    .filter(bookCart -> bookCart.book.id == bookId).collect(Collectors.toList());
            BookCart bookCart=null;

            if(bookCarts.size()==0)
            {

                 bookCart = new BookCart(book, userCart, quantity);
                 userCart.quantity = userCart.quantity+ quantity;
                 userCart.totalPrice = (int) (userCart.totalPrice+ book.price*addToCartDto.quantity);


            }

            if(bookCarts.size()==1)
            {
                int bookCartQuantity = bookCartRepository.getBookCartQuantity(bookId, userCart.id);
                userCart.quantity=(userCart.quantity+addToCartDto.quantity)-bookCartQuantity;
                userCart.totalPrice = (int) ((userCart.totalPrice+addToCartDto.quantity*book.price)-bookCartQuantity*book.price);
                bookCart = new BookCart(book, userCart, quantity);
                cartRepository.save(userCart);
            }

            bookCartRepository.save(bookCart);
            userCart.bookCartList.add(bookCart);
            cartRepository.save(userCart);
        }
        if (!cart.isPresent()) {
            userCart = new Cart(null, (int)book.price, false, quantity);
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
        Book book;
        for(int i=0;i<bookCartList.size();i++)
        {
            book=bookRepository.findById(bookCartList.get(i).bookCartID.bookId).get();
            book.quantity=bookCartList.get(i).bookQuantity;
            bookList.add(book);
        }

        return new Response("BookList", 200, bookList);

    }

    @Override
    public Response updateCart(String token) throws MessagingException {

        Optional<User> user = validate(token);
        List<Cart> userCart = user.map(user1 -> user.get().carts).orElse(null);
        Cart cart = userCart.stream().filter(cart1 -> !cart1.placedOrder).findAny().get();
        Book book;

        for(int i=0;i<cart.bookCartList.size();i++)
        {
            int bookQuantity = cart.bookCartList.get(i).bookQuantity;
            book=bookRepository.findById(cart.bookCartList.get(i).bookCartID.bookId).get();
            book.quantity=book.quantity-bookQuantity;
            bookRepository.save(book);
        }
        cart.placedOrder = true;
        cart.orderPlacedDate = LocalDateTime.now();
        String body=orderPlaceTemplet.placeOrderTemplet(cart ,user.get());
        sendMail.sendMail(new NotificationDto(user.get().email,"Order Confirmation",body));
        cartRepository.save(cart);
        return new Response("Order Placed Successfully", 200, "");

    }

    @Override
    public Response deleteBook(int id, String token) {

        Optional<User> user = validate(token);
        Cart cart = user.isPresent() ? user.get().carts.stream().filter(cart1 -> !cart1.placedOrder).findAny().get() : null;
        Optional<Book> savedBook = bookRepository.findById(id);

        int bookQuantity = bookCartRepository.getBookCartQuantity(id, cart.id);
        int quantity = cart.quantity - bookQuantity;
        int totalPrice= (int) (cart.totalPrice-bookQuantity-savedBook.get().price*bookQuantity);
        bookCartRepository.deleteBook(id, cart.id);
        cart.quantity = quantity;
        cart.totalPrice=totalPrice;
        cartRepository.save(cart);
        return new Response("Book is removed", 200, "");
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
            Book book;
            for(int i=0;i<cart.bookCartList.size();i++)
            {
                book=bookRepository.findById(cart.bookCartList.get(i).bookCartID.bookId).get();
                book.quantity=cart.bookCartList.get(i).bookQuantity;
                bookList.add(book);
            }
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