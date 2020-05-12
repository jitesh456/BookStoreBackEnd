package com.bookstoreapp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookDto {

    @NotEmpty(message = "Book name should not be null")
    public String name;

    @Min(100)
    @NotNull(message = "price should not be empty")
    public Double price;

    @NotNull(message = "quantity should not be null")
    public int quantity;

    @Size(min=3,max = 20)
    @NotNull(message = "author name should not be null")
    public String authorName;

    @NotEmpty(message = "book cover should not be null")
    public String bookCover;

    @Size(min = 10,max = 13)
    @NotNull(message = "isbn should not be null")
    public String isbn;


    @NotNull(message = "category should not be null")
    public String category;

    @Size(min = 10,max = 500)
    public String bookDetails;

    public BookDto() { }

    public BookDto(String name, Double price, int quantity, String authorName, String bookCover, String isbn,
                   String category,
                   String bookDetails) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.authorName = authorName;
        this.bookCover = bookCover;
        this.isbn = isbn;
        this.category = category;
        this.bookDetails = bookDetails;
    }
}
