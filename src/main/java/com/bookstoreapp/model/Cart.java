package com.bookstoreapp.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public LocalDateTime createdTimeStamp=LocalDateTime.now();

    public LocalDateTime orderPlacedDate;

    public int totalPrice;

    public String placedOrder;

    public int quantity;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,targetEntity = BookCart.class)
    Set<BookCart> bookCartSet;

    public Cart() {
        bookCartSet=new HashSet<>();
    }

    public Cart( LocalDateTime orderPlacedDate, int totalPrice, String placedOrder, int quantity) {
        this.orderPlacedDate = orderPlacedDate;
        this.totalPrice = totalPrice;
        this.placedOrder = placedOrder;
        this.quantity = quantity;

    }

    public void setBookCartSet(Set<BookCart> bookCartSet) {
        this.bookCartSet = bookCartSet;
    }

    public Set<BookCart> getBookCartSet() {
        return bookCartSet;
    }

}