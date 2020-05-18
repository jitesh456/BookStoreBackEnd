package com.bookstoreapp.controller;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.CartDto;
import com.bookstoreapp.dto.UpdateBookDto;
import com.bookstoreapp.dto.UpdateCartDto;
import com.bookstoreapp.exception.BookException;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.repository.ICartRepository;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.BookService;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class BookControllerTest {

    @MockBean
    BookService bookService;

    BookDto bookDto;

    CartDto cartDto;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TestRestTemplate testRestTemplate;

    HttpHeaders headers;

    Gson gson;

    @BeforeEach
    void setUp() {
        headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        gson=new Gson();
        bookDto =new BookDto("Secret of nagas",2000.0,
                12,"Amish Tiwari","comic",
                "987564236578","sdfsfd","Adaptation of the first of J.K. Rowling's popular " +
                "children's novels about Harry Potter, a boy who learns on his eleventh birthday that he is the orphaned son " );
        cartDto=new CartDto("Secret of nagas",2000.0,12,"Amish Tiwari","987564236578","imagesrc");
    }


    @Test
    void whenBookFound_ShouldReturnProperMessage() throws Exception {
        BookDto bookDto1 =new BookDto("Naruto",200.0,
                20,"makashi kissimoto","Manga",
                "12345678","","story about ninja boy ");
        Book book =new Book(bookDto);
        Book book1 =new Book(bookDto1);
        List<Book> bookList =new ArrayList<>();
        bookList.add(book);
        bookList.add(book1);
        Mockito.when(bookService.getAllBook()).thenReturn(bookList);
        String expectedList = gson.toJson(bookList);
        MvcResult result = this.mockMvc.perform(get("/books")).andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Fetched Books",gson.fromJson(result.getResponse().
                getContentAsString(), Response.class).message);
    }

    @Test
    void givenPrice_WhenProper_ShouldReturnAllBookHavingPriceLessThenGivenPrice() throws Exception {
        BookDto bookDto1 =new BookDto("Naruto",200.0,
                20,"makashi kissimoto","Manga",
                "12345678","","story about ninja boy ");
        Book book =new Book(bookDto);
        Book book1 =new Book(bookDto1);
        List<Book> bookList =new ArrayList<>();
        bookList.add(book1);
        Mockito.when(bookService.getSortedBook(any())).thenReturn(bookList);
        String expectedList=gson.toJson(bookList);
        MvcResult result=this.mockMvc.perform(get("/books/field?field=price")).andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Book List is Sorted On basic of given field",gson.fromJson(result.getResponse()
                .getContentAsString(), Response.class).message);
    }


    @Test
    void givenPrice_WhenNull_ShouldReturnErrorMessage() throws Exception {
        BookDto bookDto1 =new BookDto("Naruto",200.0,
                20,"makashi kissimoto","Manga",
                "12345678","","story about ninja boy ");
        Book book =new Book(bookDto);
        Book book1 =new Book(bookDto1);
        List<Book> bookList =new ArrayList<>();
        bookList.add(book1);
        Mockito.when(bookService.getSortedBook(any())).thenReturn(bookList);
        String expectedList=gson.toJson(bookList);
        MvcResult result=this.mockMvc.perform(get("/books/field?field=")).andReturn();
        Assert.assertEquals(400,gson.fromJson(result.getResponse()
                .getContentAsString(), Response.class).statusCode);
        Assert.assertEquals("Field cant be null for sorting",gson.fromJson(result.getResponse()
                .getContentAsString(), Response.class).message);
    }

    @Test
    void givenASortField_WhenNull_ShouldThrowException()throws Exception {
        try{
                BookDto bookDto1 = new BookDto("Naruto", 200.0,
                        20, "makashi kissimoto", "Manga",
                        "12345678", "", "story about ninja boy ");
                Book book = new Book(bookDto);
                Book book1 = new Book(bookDto1);
                List<Book> bookList = new ArrayList<>();
                bookList.add(book);
                bookList.add(book1);
                Mockito.when(bookService.getSortedBook(any())).thenThrow( new BookException("SORT FIELD CAN NOT NULL",BookException.ExceptionType.SORT_FIELD_CAN_NOT_NULL));
                MvcResult result = this.mockMvc.perform(get("/books/field?field=")).andReturn();
        }catch(BookException e){
            Assert.assertEquals(BookException.ExceptionType.SORT_FIELD_CAN_NOT_NULL,e.exceptionType);}
    }

    @Test
    void givenBook_WhenAddedToCart_ShouldReturnProperMessage() throws Exception {
        String cartDto=new Gson().toJson(this.cartDto);


        Mockito.when(bookService.addToCart(any())).thenReturn("Inserted Successfully");
        MvcResult result=this.mockMvc.perform(post("/book")
                .content(cartDto)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Book Added To Cart",
                new Gson().fromJson(result.getResponse().getContentAsString(),Response.class).message);
    }

    @Test
    void givenBookQuantity_WhenUpdated_ShouldReturnProperMessage() throws Exception {
        UpdateCartDto updateCartDto=new UpdateCartDto("1234567895",14);
        String cartDtoString=gson.toJson(updateCartDto);
        Mockito.when(bookService.updateQuantity(any())).thenReturn("Book Quantity Updated");
        MvcResult result=this.mockMvc.perform(put("/book")
                .content(cartDtoString)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Book Quantity Updated",
                new Gson().fromJson(result.getResponse().getContentAsString(),Response.class).message);
    }

    @Test
    void givenBookISBN_WhenDeleted_ShouldReturnProperMessage() throws Exception {
        String ISBN="1234567894";
        String cartDtoString=gson.toJson(ISBN);
        Mockito.when(bookService.removeFromCart(any())).thenReturn("Book Deleted Successfully");
        MvcResult result=this.mockMvc.perform(delete("/book?ISBN="+ISBN+" ")
                .content(cartDtoString)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Book Deleted Successfully",
                new Gson().fromJson(result.getResponse().getContentAsString(),Response.class).message);
    }
}
