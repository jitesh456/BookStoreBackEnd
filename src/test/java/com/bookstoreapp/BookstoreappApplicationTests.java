package com.bookstoreapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@SpringBootTest
@PropertySource("application.properties")
class BookstoreappApplicationTests {

    @Autowired
    Environment environment;

    @Test
    void contextLoads() {
    }

}
