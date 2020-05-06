package com.bookstoreapp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookDto {

    @NotEmpty(message = "Book name should not be null")
    private String name;

    @Min(100)
    @NotNull(message = "price should not be empty")
    private Double price;

    @NotNull(message = "quantity should not be null")
    private int quantity;

    @Size(min=3,max = 10)
    @NotNull(message = "author name should not be null")
    private String authorName;

    @NotEmpty(message = "book cover should not be null")
    private String bookCover;

    @Size(min = 10,max = 13)
    @NotNull(message = "isbn should not be null")
    private String isbn;


    @NotNull(message = "category should not be null")
    private String category;

    @Size(min = 10,max = 250)
    private String bookDetails;

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

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getBookCover() {
        return bookCover;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCategory() {
        return category;
    }

    public String getBookDetails() {
        return bookDetails;
    }

}
