package com.bookstoreapp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookCartId implements Serializable {


    @Column(name="bookId")
    public int bookId;

    @Column(name="cartId")
    public int cartId;

    public BookCartId(int bookId, int cartId) {
        this.bookId =bookId;
        this.cartId =cartId;
    }

    public BookCartId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCartId that = (BookCartId) o;
        return bookId == that.bookId &&
                cartId == that.cartId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, cartId);
    }
}