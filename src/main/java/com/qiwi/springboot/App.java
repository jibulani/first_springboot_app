package com.qiwi.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by etrofimov on 18.07.17.
 */

@SpringBootApplication
@EnableAutoConfiguration
public class App {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
