package com.bookstoreapp.dto;

public class AddToCartDto {

    public int bookId;
    public int quantity;

    public AddToCartDto(int bookId,  int quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }
}
