package com.bookstoreapp.dto;

public class FeedbackDto {

    public Integer rating;
    public String feedbackMessage;
    public String isbn;

    public FeedbackDto(Integer rating, String feedbackMessage , String isbn) {
        this.rating = rating;
        this.feedbackMessage = feedbackMessage;
        this.isbn = isbn;
    }
}