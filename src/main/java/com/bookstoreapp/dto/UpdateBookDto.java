package com.bookstoreapp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateBookDto {
    @Min(value = 100,message = "Price must be greater then 100")
    @NotNull(message = "price should not be empty")
    public Double price;

    @Size(min = 10,max = 13,message = "must be greater than or equal to 10")
    @NotNull(message = "isbn should not be null")
    public String isbn;

    @Min(0)
    @NotNull(message = "quantity should not be null")
    public int quantity;

    public UpdateBookDto() {
    }

    public UpdateBookDto(Double price, String isbn,int quantity) {
        this.price = price;
        this.isbn = isbn;
        this.quantity=quantity;
    }
}
