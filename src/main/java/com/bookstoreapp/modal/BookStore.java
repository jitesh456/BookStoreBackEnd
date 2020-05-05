package com.bookstoreapp.modal;

import com.bookstoreapp.dto.BookStoreDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bookdetails")
public class BookStore {


    private String name;


    private double price;

    private int quantity;


    private String bookcover;


    private String category;


    private String authorname;


    private String bookdetails;


    private String isbn;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public BookStore(BookStoreDto bookStoreDto) {
        this.name=bookStoreDto.getName();
        this.price=bookStoreDto.getPrice();
        this.quantity=bookStoreDto.getQuantity();
        this.bookcover=bookStoreDto.getBookCover();
        this.category=bookStoreDto.getCategory();
        this.authorname=bookStoreDto.getAuthorName();
        this.bookdetails=bookStoreDto.getBookDetails();
        this.isbn=bookStoreDto.getIsbn();
    }

    public Integer getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getBookdetails() {
        return bookdetails;
    }

    public void setBookdetails(String bookdetails) {
        this.bookdetails = bookdetails;
    }

    public BookStore() {
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

    public String getBookcover() {
        return bookcover;
    }

    public void setBookcover(String bookcover) {
        this.bookcover = bookcover;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
