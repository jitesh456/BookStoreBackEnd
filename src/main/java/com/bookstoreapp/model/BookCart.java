package com.bookstoreapp.model;


import javax.persistence.*;

@Entity
@Table(name="bookCart")
public class BookCart  {


    @EmbeddedId
    @Column
    public BookCartID bookCartID;


    @ManyToOne()
    @MapsId("book_Id")
    public Book book;

    @ManyToOne()
    @MapsId("cart_Id")
    public Cart cart;

    public int bookquantity;



    public BookCart() {
    }

    public BookCart( Book book,Cart cart,int bookquantity) {
        this.book=book;
        this.cart=cart;
        this.bookquantity = bookquantity;
        bookCartID=new BookCartID(book.id,cart.id);
    }


}

