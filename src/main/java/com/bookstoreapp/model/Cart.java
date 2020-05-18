package com.bookstoreapp.model;

import com.bookstoreapp.dto.CartDto;
import javax.persistence.*;

@Entity
@Table( name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private double price;

    private int quantity;

    private String bookCover;

    private String isbn;

    private String authorName;

    public Cart() {
    }

    public Cart(CartDto cartDto) {
        this.name=cartDto.bookName;
        this.isbn=cartDto.isbn;
        this.bookCover=cartDto.bookCover;
        this.quantity=cartDto.quantity;
        this.price=cartDto.price;
        this.authorName=cartDto.authorName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
