package com.bookstoreapp.response;


public class FeedbackResponse {

    public int rating;
    public String feedbackMessage;
    public String name;

    public FeedbackResponse(int Rating, String FeedbackMessage, String name) {
        rating = Rating;
        feedbackMessage = FeedbackMessage;
        this.name = name;
    }
}
