package com.bookstoreapp.model;

import javax.persistence.*;


@Entity
@Table(name="BookFeedback")
public class BookFeedback {

    @EmbeddedId
    @Column
    public BookFeedbackId bookFeedbackId;

    @ManyToOne()
    @MapsId("bookId")
    public Book book;

    @ManyToOne()
    @MapsId("feedbackId")
    public Feedback feedback;



    public BookFeedback() {
    }

    public BookFeedback( Book book,Feedback feedback) {
        this.book = book;
        this.feedback = feedback;

        bookFeedbackId=new BookFeedbackId(book.id,feedback.id);
    }



}


