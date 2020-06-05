package com.bookstoreapp.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookCartID implements Serializable {

    public int book_Id;

    public int cart_Id;

    public BookCartID(int bookId, int cartId) {
        this.book_Id=bookId;
        this.cart_Id=cartId;
    }

    public BookCartID() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCartID that = (BookCartID) o;
        return book_Id == that.book_Id &&
                cart_Id == that.cart_Id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_Id, cart_Id);
    }
}
