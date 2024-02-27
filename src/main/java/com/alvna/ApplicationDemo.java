package com.alvna;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ApplicationDemo {
    public static void main(String[] args) {
        log.info("Application Demo Server");
        SpringApplication.run(ApplicationDemo.class, args);
    }
}