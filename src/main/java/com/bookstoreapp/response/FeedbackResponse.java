package com.bookstoreapp.response;

import com.bookstoreapp.model.Feedback;

public class FeedbackResponse {

    public Feedback feedback;
    public String name;

    public FeedbackResponse(Feedback feedback, String name) {

        this.feedback = feedback;
        this.name = name;
    }
}
