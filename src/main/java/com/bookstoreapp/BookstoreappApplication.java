package com.bookstoreapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@SpringBootApplication
@PropertySource("application.properties")
public class BookstoreappApplication {

    @Autowired
    Environment environment;

    @Value("${APP_HOME}")
    private static String name;

    public static void main(String[] args) {
        SpringApplication.run(BookstoreappApplication.class, args);
        System.out.println(name);
    }

}
