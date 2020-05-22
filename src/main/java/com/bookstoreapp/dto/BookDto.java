package com.bookstoreapp.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookDto {

    @NotEmpty(message = "Book name should not be null")
    public String name;

    @Min(value = 100,message = "Price must be greater than 100")
    @NotNull(message = "Price should not be empty")
    public Double price;

    @NotNull(message = "Quantity should not be null")
    public int quantity;

    @Size(min=3,max = 20)
    @NotNull(message = "Author name should not be null")
    public String authorName;

    @NotEmpty(message = "Book cover should not be null")
    public String bookCover;

    @Size(min = 10,max = 13)
    @NotNull(message = "ISBN should not be null")
    public String isbn;


    @NotNull(message = "Category should not be null")
    public String category;

    @Size(min = 10,max = 500,message = "Book Details should include 10 to 500 characters")
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
