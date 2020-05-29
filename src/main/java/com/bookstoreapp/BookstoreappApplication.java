package com.bookstoreapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@SpringBootApplication

@PropertySource(value="application.properties",ignoreResourceNotFound = true)
public class BookstoreappApplication {

    @Autowired
    Environment environment;



    public static void main(String[] args) {
        SpringApplication.run(BookstoreappApplication.class, args);

    }

}
