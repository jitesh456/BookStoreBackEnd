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

    public String userName;

    public int rating;

    public String feedbackMessage;

    public Feedback(){}

    public Feedback(String userName, int rating ,  String feedbackMessage) {
        this.userName = userName;
        this.rating = rating;
        this.feedbackMessage = feedbackMessage;
    }
}
