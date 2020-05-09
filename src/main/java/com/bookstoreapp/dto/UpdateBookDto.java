package com.bookstoreapp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateBookDto {
    @Min(100)
    @NotNull(message = "price should not be empty")
    private Double price;

    @Size(min = 10,max = 13)
    @NotNull(message = "isbn should not be null")
    private String isbn;

    @NotNull(message = "quantity should not be null")
    private int quantity;

    public UpdateBookDto() {
    }

    public UpdateBookDto(Double price, String isbn,int quantity) {
        this.price = price;
        this.isbn = isbn;
        this.quantity=quantity;
    }

    public Double getPrice() {
        return price;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getQuantity() {
        return quantity;
    }
}
