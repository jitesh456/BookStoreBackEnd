package com.bookstoreapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public int userId;

    public int rating;

    public String feedbackMessage;

    @ManyToOne()
    @JoinColumn(name="bookId", nullable=false)
    public Book book;



    public Feedback(){}

    public Feedback(int userId, int rating ,  String feedbackMessage, Book book) {
        this.userId = userId;
        this.rating = rating;
        this.feedbackMessage = feedbackMessage;
        this.book=book;
    }
}
