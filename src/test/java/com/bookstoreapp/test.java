package com.bookstoreapp;


import com.bookstoreapp.dto.UpdateBookDto;
import com.bookstoreapp.service.Implementation.BookService;
import org.junit.Test;

public class test {

    @Test
    public void name() {
        UpdateBookDto bookDto1 =new UpdateBookDto(2000.0, "1234567895",5);
        String ac=new BookService().updatePrice(bookDto1);
        System.out.println(ac);
    }
}
