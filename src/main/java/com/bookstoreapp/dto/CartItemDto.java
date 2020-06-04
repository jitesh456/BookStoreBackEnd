package com.bookstoreapp.dto;

public class CartItemDto {
    public int bookId;
     public int quantity;

    public CartItemDto(int bookId, int quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }
}
