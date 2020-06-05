package com.bookstoreapp;

import com.bookstoreapp.properties.ApplicationProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

@EnableConfigurationProperties(ApplicationProperties.class)
class BookstoreappApplicationTests {



    @Test
    void contextLoads() {
    }

}
