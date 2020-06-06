package com.bookstoreapp.model;


import javax.persistence.*;

@Entity
@Table(name="bookCart")
public class BookCart  {


    @EmbeddedId
    @Column
    public BookCartID bookCartID;


    @ManyToOne(fetch=FetchType.LAZY)
    @MapsId("book_Id")
    private Book book;

    @ManyToOne(fetch=FetchType.LAZY)
    @MapsId("cart_Id")
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

