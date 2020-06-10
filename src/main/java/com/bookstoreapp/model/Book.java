package com.bookstoreapp.model;

import com.bookstoreapp.dto.BookDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "book",targetEntity=BookCart.class)
    @JsonIgnore
    List<BookCart> bookCartList;

    public Book(){
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


}