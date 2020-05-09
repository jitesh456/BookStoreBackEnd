package com.bookstoreapp.controller;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.UpdateBookDto;
import com.bookstoreapp.modal.Book;
import com.bookstoreapp.response.ResponseDto;
import com.bookstoreapp.service.IBookService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    BookController bookControllerTest;

    @MockBean
    BookService ibookService;

    BookDto bookDto;

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
        bookDto =new BookDto("Rajnish",2000.0,
                12,"dfsdfsf","comic",
                "987564236578","sdfsfd","Adaptation of the first of J.K. Rowling's popular " +
                "children's novels about Harry Potter, a boy who learns on his eleventh birthday that he is the orphaned son " );
        gson=new Gson();
    }

    @Test
    void givenBookData_WhenInserted_ReturnProperMessage() throws Exception {

        String bookStoreDto=new Gson().toJson(this.bookDto);
        Mockito.when(ibookService.addBook(any())).thenReturn("Inserted Successful");
        MvcResult result = this.mockMvc.perform(post("/admin/update/book")
                .content(bookStoreDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Inserted",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDto.class).message);
    }



    @Test
    void givenBookData_WhenAuthorNull_ReturnProperMessage() throws Exception {
       BookDto bookDto1 =new BookDto("Rajnish",2000.0,
                12,null,"comic",
                "1234567895","sdfsfd","Adaptation of the first of J.K. Rowling's popular " +
               "closest allies and help him discover the truth about his parents' mysterious deaths.");

        String bookStoreDtoString = gson.toJson(bookDto1);
        MvcResult result = this.mockMvc.perform(post("/admin/update/book")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("author name should not be null",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDto.class).message);
    }

    @Test
    void givenBookData_WhenBookNameNull_ReturnProperMessage() throws Exception {
        BookDto bookDto1 =new BookDto(null,2000.0,
                12,"Jitesh","comic",
                "1234567895","sdfsfd","Adaptation of the first of J.K. Rowling's popular " +
                "closest allies and help him discover the truth about his parents' mysterious deaths.");

        String bookStoreDtoString = gson.toJson(bookDto1);
        MvcResult result = this.mockMvc.perform(post("/admin/update/book")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Book name should not be null",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDto.class).message);
    }



    @Test
    void givenBookData_WhenBookCoverNull_ReturnProperMessage() throws Exception {
        BookDto bookDto1 =new BookDto("Rajnish",2000.0,
                12,"jitesh",null,
                "1234567895","sdfsfd","Adaptation of the first of J.K. Rowling's popular " +
                "closest allies and help him discover the truth about his parents' mysterious deaths.");

        String bookStoreDtoString = gson.toJson(bookDto1);
        MvcResult result = this.mockMvc.perform(post("/admin/update/book")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("book cover should not be null",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDto.class).message);
    }

    @Test
    void givenBookData_WhenIsbnNull_ReturnProperMessage() throws Exception {
        BookDto bookDto1 =new BookDto("Rajnish",2000.0,
                12,"Jitesh","comic",
                null,"sdfsfd","Adaptation of the first of J.K. Rowling's popular " +
                "closest allies and help him discover the truth about his parents' mysterious deaths.");

        String bookStoreDtoString = gson.toJson(bookDto1);
        MvcResult result = this.mockMvc.perform(post("/admin/update/book")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("isbn should not be null",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDto.class).message);
    }


    @Test
    void givenBookData_WhenIsbnLessThenTen_ReturnProperMessage() throws Exception {
        BookDto bookDto1 =new BookDto("Rajnish",2000.0,
                12,"Jitesh","comic",
                "123456","sdfsfd","Adaptation of the first of J.K. Rowling's popular " +
                "closest allies and help him discover the truth about his parents' mysterious deaths.");

        String bookStoreDtoString = gson.toJson(bookDto1);
        MvcResult result = this.mockMvc.perform(post("/admin/update/book")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("size must be between 10 and 13",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDto.class).message);
    }

    @Test
    void givenBookData_WhenCategoryNull_ReturnProperMessage() throws Exception {
        BookDto bookDto1 =new BookDto("Rajnish",2000.0,
                12,"Rahul","comic",
                "1234567895",null,"Adaptation of the first of J.K. Rowling's popular " +
                "closest allies and help him discover the truth about his parents' mysterious deaths.");

        String bookStoreDtoString = gson.toJson(bookDto1);
        MvcResult result = this.mockMvc.perform(post("/admin/update/book")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("category should not be null",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDto.class).message);
    }

    @Test
    void givenBookData_WhenDetailsEmpty_ReturnProperMessage() throws Exception {
        BookDto bookDto1 =new BookDto("Rajnish",200.0,
                12,"makashi","comic",
                "1234569875","Adventure","");

        String bookStoreDtoString = gson.toJson(bookDto1);
        MvcResult result = this.mockMvc.perform(post("/admin/update/book")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("size must be between 10 and 250",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDto.class).message);
    }

    @Test
    void givenWrongUrlPath_WhenChecked_ShouldReturnIncorrectUrlMessage() throws Exception {

        String json=gson.toJson(this.bookDto);
        this.mockMvc.perform(post("/admin/book").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenWrongContentType_WhenChecked_ShouldReturnUnSupportedTypeException() throws Exception {

        String json=gson.toJson(this.bookDto);
        this.mockMvc.perform(post("/admin/update/book").content(json)
                .contentType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void givenIncorrectMethod_WhenChecked_ShouldReturnMethodNotAllowed() throws Exception {

        String json=gson.toJson(this.bookDto);
        this.mockMvc.perform(get("/admin/update/book").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }


    @Test
    void getAllData() throws Exception {
        BookDto bookDto1 =new BookDto("Naruto",200.0,
                20,"makashi kissimoto","Manga",
                "12345678","","story about ninja boy ");
        Book book =new Book(bookDto);
        Book book1 =new Book(bookDto1);
        List<Book> bookList =new ArrayList<>();
        bookList.add(book);
        bookList.add(book1);
        Mockito.when(ibookService.getAllBook()).thenReturn(bookList);
        String expectedList = gson.toJson(bookList);
        MvcResult result = this.mockMvc.perform(get("/admin/books")).andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Request Success",gson.fromJson(result.getResponse().getContentAsString(),ResponseDto.class).message);
    }

    @Test
    void givenBookDataPrice_WhenUpdated_ReturnProperMessage() throws Exception {
        UpdateBookDto bookDto1 =new UpdateBookDto(2000.0, "1234567895");

        String bookStoreDtoString = gson.toJson(bookDto1);
        Mockito.when(ibookService.addBook(any())).thenReturn("Updated Successful");
        MvcResult result = this.mockMvc.perform(post("/admin/update/price")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Updated",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDto.class).message);
    }
}
