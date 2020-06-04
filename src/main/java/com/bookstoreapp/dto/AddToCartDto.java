package com.bookstoreapp.dto;

import com.bookstoreapp.model.Book;

import java.util.List;


public class AddToCartDto {

    public List bookId;
    public int price;
    public int Quantity;

    public AddToCartDto(List bookId, int price, int quantity) {
        this.bookId = bookId;
        this.price = price;
        Quantity = quantity;
    }
}
