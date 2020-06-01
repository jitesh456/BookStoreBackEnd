package com.bookstoreapp.model;

import javax.persistence.*;

@Entity(name="UserBook")
public class bookUser {

    @EmbeddedId
    public BookUserId bookUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("isbn")
    private Book book;

    @Column(name = "quantity")
    private int quantity;

    public bookUser() {
    }

    public bookUser(User user,Book book,int quantity) {
        this.book=book;
        this.user=user;
        this.quantity=quantity;
        bookUserId=new BookUserId(user.id,book.isbn);
    }

}
