package com.bookstoreapp.dto;

public class AddToCartDto {

    public int bookId;
    public int Quantity;

    public AddToCartDto(int bookId,  int quantity) {
        this.bookId = bookId;
        Quantity = quantity;
    }
}
