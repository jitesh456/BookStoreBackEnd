package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.CartDto;
import com.bookstoreapp.dto.UpdateBookDto;
import com.bookstoreapp.dto.UpdateCartDto;
import com.bookstoreapp.exception.BookException;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.model.Cart;
import com.bookstoreapp.repository.IBookRepository;
import com.bookstoreapp.repository.ICartRepository;
import com.bookstoreapp.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    IBookRepository iBookRepository;

    @Autowired
    ICartRepository iCartRepository;

    public BookService() {
    }

    @Override
    public String addBook(BookDto bookDto) {
        Book book =new Book(bookDto);
        Optional<Book> isbn = iBookRepository.findByIsbn(bookDto.isbn);
        if (isbn.isPresent()){
            throw new BookException("BOOK ALREADY EXISTS",BookException.ExceptionType.BOOK_ALREADY_EXIST);
        }
        iBookRepository.save(book);
            return "Insertion Successful";
    }

    @Override
    public Iterable<Book> getAllBook() {
        return iBookRepository.findAll();
    }

    @Override
    public String updatePrice(UpdateBookDto bookDto) {
        Optional<Book> book=iBookRepository.findByIsbn(bookDto.isbn);
        if (book.isPresent()){
            Book book1=book.get();
            book1.setPrice(bookDto.price);
            book1.setQuantity(bookDto.quantity);
            iBookRepository.save(book1);
            return "Updated Successfully";
        }
        throw new BookException("BOOK DOES NOT EXISTS",BookException.ExceptionType.BOOK_DOES_NOT_EXIST);
    }

    @Override
    public Iterable<Book> getSortedBook(String sortField) {
        if(sortField != null) {
            return iBookRepository.findAll(Sort.by(Sort.Direction.ASC, sortField));
        }
        throw new BookException("SORT FIELD CAN NOT NULL",BookException.ExceptionType.SORT_FIELD_CAN_NOT_NULL);
    }

    @Override
    public String addToCart(CartDto cartDto) {
        Cart cart=new Cart(cartDto);
        iCartRepository.save(cart);
        return "Book Added To Cart";
    }

    @Override
    public String updateQuantity(UpdateCartDto updateCartDto) {
        Optional<Cart> book = iCartRepository.findByIsbn(updateCartDto.isbn);
        if (book.isPresent()){
            Cart book1=book.get();
            book1.setQuantity(updateCartDto.quantity);
            iCartRepository.save(book1);
            return "Book Quantity Updated";
        }
        throw new BookException("BOOK DOES NOT EXISTS",BookException.ExceptionType.BOOK_DOES_NOT_EXIST);
    }

    public String removeFromCart(String ISBN) {
        return null;
    }
}
