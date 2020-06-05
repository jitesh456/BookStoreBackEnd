package com.bookstoreapp.model;

import com.bookstoreapp.dto.BookDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="bookdetails")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public String name;


    public double price;

    public int quantity;

    public String bookcover;


    public String category;


    public String authorname;


    public String bookdetails;


    public String isbn;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL,targetEntity=BookCart.class)
    Set<BookCart> bookCartSet;

    public Book() {
        bookCartSet=new HashSet<>();
    }


    public Book(BookDto bookDto) {
        this.name= bookDto.name;
        this.price= bookDto.price;
        this.quantity= bookDto.quantity;
        this.bookcover= bookDto.bookCover;
        this.category= bookDto.category;
        this.authorname= bookDto.authorName;
        this.bookdetails= bookDto.bookDetails;
        this.isbn= bookDto.isbn;

    }

    public void setBookCartSet(Set<BookCart> bookCartSet) {
        this.bookCartSet = bookCartSet;
    }

    public Set<BookCart> getBookCartSet() {
        return bookCartSet;
    }
}