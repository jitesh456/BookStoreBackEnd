package com.bookstoreapp.dto;

public class UpdateCartDto {
    public int quantity;
    public String isbn;

    public UpdateCartDto(String isbn, int quantity) {
        this.isbn=isbn;
        this.quantity=quantity;
    }
}
