package com.bookstoreapp.model;

import com.bookstoreapp.dto.BookDto;

import javax.persistence.*;

@Entity
@Table(name = "bookdetails")
public class Book {


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

    public Book(BookDto bookDto) {
        this.name= bookDto.getName();
        this.price= bookDto.getPrice();
        this.quantity= bookDto.getQuantity();
        this.bookcover= bookDto.getBookCover();
        this.category= bookDto.getCategory();
        this.authorname= bookDto.getAuthorName();
        this.bookdetails= bookDto.getBookDetails();
        this.isbn= bookDto.getIsbn();
        this.id=null;
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

    public Book() {
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
