package com.bookstoreapp.model;


import javax.persistence.*;

@Entity
@Table(name="bookCart")
public class BookCart  {


    @EmbeddedId
    public BookCartID bookCartID;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(insertable = false,updatable =false)
    private Book book;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(insertable = false,updatable =false)
    private Cart cart;
    private int bookquantity;



    public BookCart() {
    }

    public BookCart( Book book,Cart cart,int bookquantity) {
        this.book=book;
        this.cart=cart;
        this.bookquantity = bookquantity;
        bookCartID=new BookCartID(book.id,cart.id);

    }

    public BookCartID getBookCartID() {
        return bookCartID;
    }

    public void setBookCartID(BookCartID bookCartID) {
        this.bookCartID = bookCartID;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getBookquantity() {
        return bookquantity;
    }

    public void setBookquantity(int bookquantity) {
        this.bookquantity = bookquantity;
    }
}

