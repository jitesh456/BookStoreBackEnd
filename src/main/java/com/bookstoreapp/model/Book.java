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

    public String bookCover;

    public String category;

    public String authorName;

    public String bookDetails;

    public String isbn;


    @OneToMany(mappedBy = "book",targetEntity=BookCart.class)
    @JsonIgnore
    List<BookCart> bookCartList;

    @OneToMany(mappedBy = "book",targetEntity=BookFeedback.class)
    @JsonIgnore
    List<BookFeedback> bookFeedbackList;

    public Book(){
    }

    public Book(BookDto bookDto) {
        this.name= bookDto.name;
        this.price= bookDto.price;
        this.quantity= bookDto.quantity;
        this.bookCover = bookDto.bookCover;
        this.category= bookDto.category;
        this.authorName = bookDto.authorName;
        this.bookDetails = bookDto.bookDetails;
        this.isbn= bookDto.isbn;
    }
}