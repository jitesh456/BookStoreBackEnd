package com.bookstoreapp.controller;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.UpdateBookDto;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class AdminControllerTest {

//    @Value("${image.file.path}")
//    private String imagePath;

    @MockBean
    BookService bookService;
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
        bookDto =new BookDto("Secret of nagas",2000.0,
                12,"Amish Tiwari","comic",
                "987564236578","Adventure","Adaptation of the first of J.K. Rowling's popular " +
                "children's novels about Harry Potter, a boy who learns on his eleventh birthday that he is the orphaned son " );
        gson=new Gson();
    }

    @Test
    void givenBookData_WhenInserted_ReturnProperMessage() throws Exception {
        String bookStoreDto=new Gson().toJson(this.bookDto);
        Mockito.when(bookService.addBook(any())).thenReturn("Inserted Successful");
        MvcResult result = this.mockMvc.perform(post("/admin/book")
                .content(bookStoreDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Book Added Successfully",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);
    }



    @Test
    void givenBookData_WhenAuthorNull_ReturnProperMessage() throws Exception {
       BookDto bookDto1 =new BookDto("Secret of nagas",2000.0,
                12,null,"comic",
                "1234567895","Adventure","Adaptation of the first of J.K. Rowling's popular " +
               "closest allies and help him discover the truth about his parents' mysterious deaths.");

        String bookStoreDtoString = gson.toJson(bookDto1);
        MvcResult result = this.mockMvc.perform(post("/admin/book")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Author name should not be null",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);
    }

    @Test
    void givenBookData_WhenBookNameNull_ReturnProperMessage() throws Exception {
        BookDto bookDto1 =new BookDto(null,2000.0,
                12,"Amish Tiwari","comic",
                "1234567895","sdfsfd","Adaptation of the first of J.K. Rowling's popular " +
                "closest allies and help him discover the truth about his parents' mysterious deaths.");

        String bookStoreDtoString = gson.toJson(bookDto1);
        MvcResult result = this.mockMvc.perform(post("/admin/book")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Book name should not be null",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);
    }



    @Test
    void givenBookData_WhenBookCoverNull_ReturnProperMessage() throws Exception {
        BookDto bookDto1 =new BookDto("Secret of nagas",2000.0,
                12,"Amish Tiwari",null,
                "1234567895","sdfsfd","Adaptation of the first of J.K. Rowling's popular " +
                "closest allies and help him discover the truth about his parents' mysterious deaths.");

        String bookStoreDtoString = gson.toJson(bookDto1);
        MvcResult result = this.mockMvc.perform(post("/admin/book")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Book cover should not be null",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);
    }

    @Test
    void givenBookData_WhenIsbnNull_ReturnProperMessage() throws Exception {
        BookDto bookDto1 =new BookDto("Secret of nagas",2000.0,
                12,"Amiish Tiwari","comic",
                null,"sdfsfd","Adaptation of the first of J.K. Rowling's popular " +
                "closest allies and help him discover the truth about his parents' mysterious deaths.");

        String bookStoreDtoString = gson.toJson(bookDto1);
        MvcResult result = this.mockMvc.perform(post("/admin/book")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("ISBN should not be null",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);
    }


    @Test
    void givenBookData_WhenIsbnLessThenTen_ReturnProperMessage() throws Exception {
        BookDto bookDto1 =new BookDto("Secret of nagas",2000.0,
                12,"Amish Tiwari","comic",
                "123456","sdfsfd","Adaptation of the first of J.K. Rowling's popular " +
                "closest allies and help him discover the truth about his parents' mysterious deaths.");

        String bookStoreDtoString = gson.toJson(bookDto1);
        MvcResult result = this.mockMvc.perform(post("/admin/book")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("size must be between 10 and 13",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);
    }

    @Test
    void givenBookData_WhenCategoryNull_ReturnProperMessage() throws Exception {
        BookDto bookDto1 =new BookDto("Secret of nagas",2000.0,
                12,"Amish Tiwari","comic",
                "1234567895",null,"Adaptation of the first of J.K. Rowling's popular " +
                "closest allies and help him discover the truth about his parents' mysterious deaths.");

        String bookStoreDtoString = gson.toJson(bookDto1);
        MvcResult result = this.mockMvc.perform(post("/admin/book")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Category should not be null",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);
    }

    @Test
    void givenBookData_WhenDetailsEmpty_ReturnProperMessage() throws Exception {
        BookDto bookDto1 =new BookDto("Secret of nagas",200.0,
                12,"Amish Tiwari","comic",
                "1234569875","Adventure","");

        String bookStoreDtoString = gson.toJson(bookDto1);
        MvcResult result = this.mockMvc.perform(post("/admin/book")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Book Details should include 10 to 500 characters",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);
    }

    @Test
    void givenWrongUrlPath_WhenChecked_ShouldReturnIncorrectUrlMessage() throws Exception {

        String json=gson.toJson(this.bookDto);
        this.mockMvc.perform(post("/admin/update/book").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenWrongContentType_WhenChecked_ShouldReturnUnSupportedTypeException() throws Exception {

        String json=gson.toJson(this.bookDto);
        this.mockMvc.perform(post("/admin/book").content(json)
                .contentType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void givenIncorrectMethod_WhenChecked_ShouldReturnMethodNotAllowed() throws Exception {

        String json=gson.toJson(this.bookDto);
        this.mockMvc.perform(get("/admin/book").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }


    @Test
    void givenBookDataPrice_WhenUpdated_ReturnProperMessage() throws Exception {
        UpdateBookDto bookDto1 =new UpdateBookDto(2000.0, "1234567895",5);

        String bookStoreDtoString = gson.toJson(bookDto1);
        Mockito.when(bookService.addBook(any())).thenReturn("Updated Successful");
        MvcResult result = this.mockMvc.perform(put("/admin/book")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Book is Updated",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);
    }

    @Test
    void givenBookDataPrice_WhenPriceZero_ReturnProperMessage() throws Exception {
        UpdateBookDto bookDto1 =new UpdateBookDto(0.0, "1234567895",5);

        String bookStoreDtoString = gson.toJson(bookDto1);
        MvcResult result = this.mockMvc.perform(put("/admin/book")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Price must be greater than 100",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);
    }

    @Test
    void givenBookDataPrice_WhenQuantityIsZero_ReturnProperMessage() throws Exception {
        UpdateBookDto bookDto1 =new UpdateBookDto(2000.0, null,5);

        String bookStoreDtoString = gson.toJson(bookDto1);
        MvcResult result = this.mockMvc.perform(put("/admin/book")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("ISBN should not be null",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);
    }

    @Test
    void givenBookData_WhenIsbnIsNotProper_ReturnProperMessage() throws Exception {
        UpdateBookDto bookDto1 =new UpdateBookDto(2000.0, "123456",0);
        String bookStoreDtoString = gson.toJson(bookDto1);
        MvcResult result = this.mockMvc.perform(put("/admin/book")
                .content(bookStoreDtoString)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("ISBN must include 10 or 13 characters",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);
    }
    @Test
    void givenImageAsMultipart_shouldReturnImageViewURL() throws Exception {
        MockMultipartFile imageFile = new MockMultipartFile("file","1.png",
                "image/png","Some data".getBytes());

        MvcResult result = this.mockMvc.perform(multipart("/admin/uploadImage")
                .file(imageFile))
                .andReturn();

        Assert.assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void givenFileName_WhenFound_ReturnsFile() throws Exception {
        String fileName="dragon-ball-z-kakarot-reviews.original.jpg";
        String imagePath="//src//main//resources//Images//";
        String fileBasePath = System.getProperty("user.dir")+imagePath;
        Path path = Paths.get(fileBasePath + fileName);
        Resource resource = new UrlResource(path.toUri());
        Mockito.when(bookService.loadFile(any(), any())).thenReturn(resource);

        MvcResult result = this.mockMvc.perform(get("/admin/downloadFile/fileName?fileName=imageName"))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
    }
}
