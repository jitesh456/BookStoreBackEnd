package com.bookstoreapp.dto;

import javax.validation.constraints.NotNull;

public class BookStoreDto {

    @NotNull(message = "name should not be null")
    private String name;

    @NotNull(message = "price should not be null")
    private Double price;

    @NotNull(message = "quantity should not be null")
    private int quantity;

    @NotNull(message = "author name should not be null")
    private String authorName;

    @NotNull(message = "book cover should not be null")
    private String bookCover;

    @NotNull(message = "isbn should not be null")
    private String isbn;

    @NotNull(message = "category should not be null")
    private String category;

    @NotNull(message = "book Details should not be null")
    private String bookDetails;

    public BookStoreDto(String name, Double price, int quantity, String authorName, String bookCover, String isbn,
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
