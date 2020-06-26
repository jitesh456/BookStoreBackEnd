package com.bookstoreapp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class BookFeedbackId  implements Serializable {


    @Column(name="bookId")
    public int bookId;

    @Column(name="feedbackId")
    public int feedbackId;

    public BookFeedbackId(int bookId, int feedbackId) {
        this.bookId =bookId;
        this.feedbackId =feedbackId;
    }

    public BookFeedbackId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookFeedbackId that = (BookFeedbackId) o;
        return bookId == that.bookId &&
                feedbackId == that.feedbackId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, feedbackId);
    }


}
