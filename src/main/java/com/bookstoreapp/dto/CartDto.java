package com.bookstoreapp.dto;

public class CartDto {

    public String bookCover;
    public String isbn;
    public String authorName;
    public int quantity;
    public double price;
    public String bookName;

    public CartDto(String bookName, double price, int quantity, String authorName, String isbn, String bookCover) {
        this.bookName=bookName;
        this.price=price;
        this.quantity=quantity;
        this.authorName=authorName;
        this.isbn=isbn;
        this.bookCover=bookCover;
    }
}
