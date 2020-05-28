package com.bookstoreapp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateCartDto {

    @Min(value = 0,message ="Quantity cant be less then 0")
    public int quantity;

    @NotNull(message = "ISBN  should not be null")
    @Size(min=11,max = 11,message = "ISBN must have 11 Digit")
    public String isbn;

    public UpdateCartDto(String isbn, int quantity) {
        this.isbn=isbn;
        this.quantity=quantity;
    }
}
