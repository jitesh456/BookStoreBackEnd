package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.model.Book;
import com.bookstoreapp.model.Cart;

import java.util.List;

public class OrderPlacedResponse {
    public List<Book> bookList;
    public Cart cart;

    public OrderPlacedResponse(List<Book> bookList, Cart cart) {

        this.bookList=bookList;
        this.cart=cart;
    }
}
