package com.bookstoreapp.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FeedbackDto {

    @NotNull(message = "Rating cannot be null")
    public Integer rating;

    @NotNull(message = "Feedback cannot be null")
    @Size(min = 10,max = 200,message = "Feedback must include 10-200 characters")
    public String feedbackMessage;

    public String isbn;

    public FeedbackDto() {
    }

    public FeedbackDto(Integer rating, String feedbackMessage , String isbn) {
        this.rating = rating;
        this.feedbackMessage = feedbackMessage;
        this.isbn = isbn;
    }
}