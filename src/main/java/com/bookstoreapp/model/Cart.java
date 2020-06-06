package com.bookstoreapp.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;


@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public LocalDateTime createdTimeStamp=LocalDateTime.now();

    public LocalDateTime orderPlacedDate;

    public int totalPrice;

    public Boolean placedOrder;

    public int quantity;


    @OneToMany(mappedBy = "cart",targetEntity = BookCart.class)
    public List<BookCart> bookCartList;

    public Cart() {

    }

    public Cart( LocalDateTime orderPlacedDate, int totalPrice, boolean placedOrder, int quantity) {
        this.orderPlacedDate = orderPlacedDate;
        this.totalPrice = totalPrice;
        this.placedOrder = placedOrder;
        this.quantity = quantity;

    }

    public void setBookCartList(List<BookCart> bookCartList) {
        this.bookCartList = bookCartList;
    }

    public List<BookCart> getBookCartList() {
        return bookCartList;
    }

}