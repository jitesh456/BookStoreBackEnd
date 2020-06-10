package com.bookstoreapp.response;

import com.bookstoreapp.model.Book;
import com.bookstoreapp.model.Cart;

import java.util.List;

public class OrderPlacedResponse {
    public Cart cart;
    public List<Book> bookList;

    public OrderPlacedResponse(List<Book> bookList, Cart cart) {
        this.bookList=bookList;
        this.cart=cart;
    }
}
