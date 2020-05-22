package com.bookstoreapp.model;

import com.bookstoreapp.dto.BookDto;

import javax.persistence.*;

@Entity
@Table(name = "bookdetails")
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;


    private double price;

    private int quantity;

    private String bookcover;


    private String category;


    private String authorname;


    private String bookdetails;


    private String isbn;


    public Book(BookDto bookDto) {
        this.name= bookDto.name;
        this.price= bookDto.price;
        this.quantity= bookDto.quantity;
        this.bookcover= bookDto.bookCover;
        this.category= bookDto.category;
        this.authorname= bookDto.authorName;
        this.bookdetails= bookDto.bookDetails;
        this.isbn= bookDto.isbn;
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
