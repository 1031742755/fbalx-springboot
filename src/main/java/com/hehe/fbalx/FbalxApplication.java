package com.hehe.fbalx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FbalxApplication {

    public static void main(String[] args) {
        SpringApplication.run(FbalxApplication.class, args);
    }

}
