package com.bookstoreapp.model;

import javax.persistence.Embeddable;

@Embeddable
public class BookCart {

    public int bookid;

    public int bookquantity;

    public int bookprice;

    public BookCart(int bookid, int bookquantity, int bookprice) {
        this.bookid = bookid;
        this.bookquantity = bookquantity;
        this.bookprice = bookprice;
    }
}
