package com.bookstoreapp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookUserId implements Serializable {

    @Column(name = "user_id")
    private int userId;

    @Column(name = "isbn")
    private String isbn;

    public BookUserId() {
    }

   public BookUserId(int id, String isbn) {
        userId=id;
        this.isbn=isbn;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookUserId that = (BookUserId) o;
        return userId == that.userId &&
                Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, isbn);
    }

    @Override
    public String toString() {
        return "BookUserId{" +
                "userId=" + userId +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
