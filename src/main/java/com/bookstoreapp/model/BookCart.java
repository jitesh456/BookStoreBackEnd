package com.bookstoreapp.model;


import javax.persistence.*;

@Entity
@Table(name="bookCart")
public class BookCart  {

    @EmbeddedId
    @Column
    public BookCartId bookCartID;

    @ManyToOne()
    @MapsId("bookId")
    public Book book;

    @ManyToOne()
    @MapsId("cartId")
    public Cart cart;

    public int bookQuantity;

    public BookCart() {
    }

    public BookCart( Book book,Cart cart,int bookQuantity) {
        this.book=book;
        this.cart=cart;
        this.bookQuantity = bookQuantity;
        bookCartID=new BookCartId(book.id,cart.id);
    }
}

