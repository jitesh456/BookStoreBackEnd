package com.bookstoreapp;

import com.bookstoreapp.properties.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class BookstoreappApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreappApplication.class, args);

    }

}
